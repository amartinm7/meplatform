#!/bin/bash

# docker network create meplatform-network
# docker network ls

# run with network, the profile is used to connect with the database when is running as a service into a docker
docker run --network=meplatform-network -p 8000:8000 -e SPRING_PROFILES_ACTIVE=dev -it ms-meplatform

# run without network
# docker run -p 8000:8000 -e SPRING_PROFILES_ACTIVE=dev -it ms-meplatform

# to inspect
# docker run -ti --entrypoint /bin/sh ms-meplatform