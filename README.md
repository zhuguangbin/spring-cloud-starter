# spring-cloud-starter
an archetype project for spring cloud.

## description

We use the following components:

* eureka: as discovery service

* spring cloud config: as configuration serviceΩ

* actuator: monitoring

* feign: rpc

* spring boot + data jpa : access db

* angular + ng-zorro : frontend (npm and ng cli required)


## Usage:

You can clone the repo, and import to you IDE for a starter, and then apply your own logic.

This project is completely usable, you can package it and deploy as a local service.

make sure you have deployed mysql server locally, and following the default params:

```
port: 3306
dbname: spring-demo
username: root 
password: admin
```
or you can change the application.yaml in backend module.

then, let's build the deploy it~


```
$mvn clean package -Prelease

$cd packaging/target

$tar zxvf spring-starter-1.0-SNAPSHOT-bin.tar.gz

$cd spring-starter-1.0-SNAPSHOT

$bin/daemon.sh
Unknown daemon ''. Usage: daemon.sh (start|stop) (discovery-server|config-monitor-server|backend|frontend) [args].

$ bin/daemon.sh start discovery-server
Starting discovery-server daemon on host zhuguangbin-MacBook-Pro.local.

# zhuguangbin @ zhuguangbin-MacBook-Pro in ~/Code/github/zhuguangbin/spring-cloud-starter/packaging/target/spring-starter-1.0-SNAPSHOT on git:main o [11:04:19]
$ bin/daemon.sh start config-monitor-server
Starting config-monitor-server daemon on host zhuguangbin-MacBook-Pro.local.

# zhuguangbin @ zhuguangbin-MacBook-Pro in ~/Code/github/zhuguangbin/spring-cloud-starter/packaging/target/spring-starter-1.0-SNAPSHOT on git:main o [11:04:35]
$ bin/daemon.sh start backend
Starting backend daemon on host zhuguangbin-MacBook-Pro.local.

# zhuguangbin @ zhuguangbin-MacBook-Pro in ~/Code/github/zhuguangbin/spring-cloud-starter/packaging/target/spring-starter-1.0-SNAPSHOT on git:main o [11:04:46]
$ bin/daemon.sh start frontend
Starting frontend daemon on host zhuguangbin-MacBook-Pro.local.

```

then open your browser

* web front: `http://localhost:8082/`

* enruke web ui: `http://localhost:8080/`

* spring boot admin web ui: `http://localhost:8081/`
