#!/bin/bash

if [ $# -eq 1 ] &&  [ -n "$1" ];
then
        sed -i '/nexus\.local/d' /etc/hosts

        echo "$1 nexus.local" >> /etc/hosts
else
        echo "No Nexus Host provided"
fi