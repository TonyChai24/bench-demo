#!/bin/bash
service pmcd start
service pmlogger start
service pmie start
service pmwebd start
java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar