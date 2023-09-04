#!/bin/bash

## run three instances of the fever app
docker-compose -f docker-compose-all.yml up -d

# docker-compose -f docker-compose-all.yml restart nginx

# docker-compose -f docker-compose-all.yml down
