#!/bin/bash

## run three instances of the meplatform app
docker-compose up -d --scale meplatform=3

sleep 20

## send messages in background
sh ./send_messages.sh &

## run stats
# docker stats

sleep 5

## run five instances of the meplatform app
docker-compose up -d --scale meplatform=5

docker-compose restart nginx

sleep 30

## run two instances of the meplatform app
docker-compose up -d --scale meplatform=2

docker-compose restart nginx

sleep 30

## run one instances of the meplatform app
docker-compose up -d --scale meplatform=1

docker-compose restart nginx

sleep 180

# Stop the execution of send_messages.sh script
pkill -f "./send_messages.sh"

## run three instances of the meplatform app
docker-compose down