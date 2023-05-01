#!/bin/sh

cinema-service/gradlew clean build -x test -p "cinema-service";
films-service/gradlew clean build -x test -p "films-service";
tickets-service/gradlew clean build -x test -p "tickets-service";