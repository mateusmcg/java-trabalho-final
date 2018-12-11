#!/bin/sh

./devops/build.sh

if [ $? -eq 0 ]; then
    docker-compose up --build -d
else
    echo "Ocorreu um erro ao buildar o app =("
fi