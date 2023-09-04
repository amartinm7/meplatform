#!/usr/bin/env bash

# stop docker json-schemas container
docker ps -a |grep "json-schemas"|awk '{print $1}'|xargs docker stop

# remove docker json-schemas container
docker ps -a |grep "json-schemas"|awk '{print $1}'|xargs docker rm

# remove docker image json-schemas
docker images |grep "json-schemas"|awk '{print $3}'|xargs docker rmi

# create image
docker build -t json-schemas .

# run docker container
docker run --name any-nginx -d -p 8080:8080 json-schemas

# curl for testing
# curl -X GET 'http://localhost:8080/examples/identity/UserCreated-Event.json/1.0.json'
