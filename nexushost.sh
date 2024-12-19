#!/bin/bash

if [ $# -eq 1 ] &&  [ -n "$1" ];
then
        sed -i '/nexus\.local/d' /etc/hosts

        echo "$1 nexus.local" >> /etc/hosts

        echo "{ \"insecure-registries\": [\"nexus.local:8083\"] }" > /etc/docker/daemon.json

        systemctl restart docker
else
        echo "No Nexus Host provided"
fi