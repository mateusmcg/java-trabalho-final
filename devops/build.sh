#!/bin/sh

if [[ "$OSTYPE" == "linux-gnu" ]]; then
    mvn package #build
elif [[ "$OSTYPE" == "darwin"* ]]; then
    ./mvnw package #build
elif [[ "$OSTYPE" == "win32" ]]; then
    ./mvnw package #build
fi
