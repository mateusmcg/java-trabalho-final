#!/bin/sh

if [[ "$OSTYPE" == "linux-gnu" ]]; then
    mvn package #build
elif [[ "$OSTYPE" == "darwin"* ]]; then
    ./mvnw package #build
else
    ./mvnw package #build
fi
