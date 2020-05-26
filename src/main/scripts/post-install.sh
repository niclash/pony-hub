#!/bin/sh
USER=ponyactor
GROUP=nogroup
adduser --system --quiet $USER
LOG_DIR=/var/log/pony/ponyactor
if [ ! -d  $LOG_DIR ] ; then
    mkdir -p $LOG_DIR
fi
chown $USER:$GROUP $LOG_DIR

systemctl enable ponyactor
service ponyactor start
