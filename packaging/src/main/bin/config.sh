#!/usr/bin/env bash

PROJECT_HOME=$(cd "$bin"/..;pwd)
PROJECT_LIB_DIR=$PROJECT_HOME/lib
PROJECT_CONF_DIR=$PROJECT_HOME/conf
PROJECT_PID_DIR=$PROJECT_HOME/pid
PROJECT_LOG_DIR=$PROJECT_HOME/logs

DISCOVERY_SERVER_JAR="$(find "$PROJECT_LIB_DIR" -name 'discovery-server*.jar')"
CONFIG_MONITOR_SERVER_JAR="$(find "$PROJECT_LIB_DIR" -name 'config-monitor-server*.jar')"
BACKEND_JAR="$(find "$PROJECT_LIB_DIR" -name 'backend*.jar')"
FRONTEND_JAR="$(find "$PROJECT_LIB_DIR" -name 'frontend*.jar')"

UNAME=$(uname -s)
if [ "${UNAME:0:6}" == "CYGWIN" ]; then
    JAVA_RUN=java
else
    if [[ -d $JAVA_HOME ]]; then
        JAVA_RUN=$JAVA_HOME/bin/java
    else
        JAVA_RUN=java
    fi
fi

# Define HOSTNAME if it is not already set
if [ -z "${HOSTNAME}" ]; then
    HOSTNAME=`hostname`
fi

IS_NUMBER="^[0-9]+$"
