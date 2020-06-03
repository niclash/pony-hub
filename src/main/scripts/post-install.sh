#!/bin/sh
USER=pony
GROUP=nogroup
adduser --system --quiet $USER
LOG_DIR=/var/log/ponyhub
if [ ! -d  $LOG_DIR ] ; then
    mkdir -p $LOG_DIR
fi
chown $USER:$GROUP $LOG_DIR

systemctl enable ponyhub
service ponyhub start
