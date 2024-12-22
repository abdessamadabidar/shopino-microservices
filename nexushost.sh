#!/bin/bash

if [ $# -eq 1 ] &&  [ -n "$1" ];
then
        echo "{ \"insecure-registries\": [\"$1:8083\"] }" > /etc/docker/daemon.json

        systemctl restart docker
else
        echo "No Nexus Host provided"
fi