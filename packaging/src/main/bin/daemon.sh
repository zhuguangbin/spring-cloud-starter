#!/usr/bin/env bash

# Start/stop a daemon.
USAGE="Usage: daemon.sh (start|stop) (discovery-server|config-monitor-server|backend|frontend) [args]"

STARTSTOP=$1
DAEMON=$2
ARGS=("${@:3}") # get remaining arguments as array

bin=`dirname "$0"`
bin=`cd "$bin"; pwd`

. "$bin"/config.sh

case $DAEMON in
    (discovery-server)
        JAR_TO_RUN=${DISCOVERY_SERVER_JAR}
    ;;

    (config-monitor-server)
        JAR_TO_RUN=${CONFIG_MONITOR_SERVER_JAR}
    ;;

    (backend)
        JAR_TO_RUN=${BACKEND_JAR}
    ;;

    (frontend)
        JAR_TO_RUN=${FRONTEND_JAR}
    ;;

    (*)
        echo "Unknown daemon '${DAEMON}'. $USAGE."
        exit 1
    ;;
esac

if [ "$PROJECT_IDENT_STRING" = "" ]; then
    PROJECT_IDENT_STRING="$USER"
fi

pid=$PROJECT_PID_DIR/$DAEMON.pid

mkdir -p "$PROJECT_PID_DIR"

# Log files for daemons are indexed from the process ID's position in the PID
# file. The following lock prevents a race condition during daemon startup
# when multiple daemons read, index, and write to the PID file concurrently.
# The lock is created on the PID directory since a lock file cannot be safely
# removed. The daemon is started with the lock closed and the lock remains
# active in this script until the script exits.
command -v flock >/dev/null 2>&1
if [[ $? -eq 0 ]]; then
    exec 200<"$PROJECT_PID_DIR"
    flock 200
fi

PROJECT_LOG_PREFIX="${PROJECT_LOG_DIR}/${DAEMON}-${PROJECT_IDENT_STRING}-${HOSTNAME}"
out="${PROJECT_LOG_PREFIX}.out"

PROJECT_STOP_TIMEOUT=${PROJECT_STOP_TIMEOUT:-5}

JAVA_VERSION=$(${JAVA_RUN} -version 2>&1 | sed 's/.*version "\(.*\)\.\(.*\)\..*"/\1\2/; 1q')

# Only set JVM 8 arguments if we have correctly extracted the version
if [[ ${JAVA_VERSION} =~ ${IS_NUMBER} ]]; then
    if [ "$JAVA_VERSION" -lt 18 ]; then
        JVM_ARGS="$JVM_ARGS -XX:MaxPermSize=256m"
    fi
fi

case $STARTSTOP in

    (start)

        if [ -f $pid ]; then
          if kill -0 `cat $pid` > /dev/null 2>&1; then
          echo $DAEMON running as process `cat $pid`.  Stop it first.
          exit 1
          fi
        fi

        echo "Starting $DAEMON daemon on host $HOSTNAME."
        cd ${PROJECT_HOME}
#        CONF_FOLDER=${PROJECT_CONF_DIR}/${DAEMON}/startup.conf
        source ${PROJECT_CONF_DIR}/${DAEMON}/startup.conf
        $JAVA_RUN $JVM_ARGS ${JAVA_OPTS} -Duser.language=zh -Duser.region=CN -Dfile.encoding=UTF-8 -Dspring.config.location=file:${PROJECT_CONF_DIR}/${DAEMON}/ -jar ${JAR_TO_RUN} "${ARGS[@]}" > "$out" 200<&- 2>&1 < /dev/null &

        mypid=$!

        # Add to pid file if successful start
        if [[ ${mypid} =~ ${IS_NUMBER} ]] && kill -0 $mypid > /dev/null 2>&1 ; then
            echo $mypid > "$pid"
        else
            echo "Error starting $DAEMON daemon."
            exit 1
        fi
    ;;

    (stop)
        if [ -f "$pid" ]; then
            to_stop=$(cat $pid)

            if kill -0 $to_stop > /dev/null 2>&1; then
              echo "Stopping $DAEMON daemon (pid: $to_stop) on host $HOSTNAME."
              kill $to_stop
              sleep $PROJECT_STOP_TIMEOUT
              if kill -0 $to_stop > /dev/null 2>&1; then
                echo "$DAEMON did not stop gracefully after $PROJECT_STOP_TIMEOUT seconds: killing with kill -9"
                kill -9 $to_stop
              fi
            else
              echo "No $DAEMON daemon (pid: $to_stop) is running anymore on $HOSTNAME."
            fi
            rm -f $pid

        else
            echo "No $DAEMON daemon to stop on host $HOSTNAME."
        fi
    ;;

    (*)
        echo "Unexpected argument '$STARTSTOP'. $USAGE."
        exit 1
    ;;

esac
