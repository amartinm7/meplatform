docker build -t schemas .

docker network create json-schema-validation-network

docker run -d \
  --network json-schema-validation-network \
  --network-alias schemas \
  --name nginx-schemas \
  --rm \
  schemas

docker run \
  --pull=always \
  --network json-schema-validation-network \
  --network-alias validation \
  --rm \
  -e SCHEMA_HOST=http://schemas:8080/ \
  -v "$(pwd)/schema:/schema" \
  -v "$(pwd)/test/assets:/assets" \
  containers.mpi-internal.com/scmspain/tools-common-event-formats-checker

failures=$?

docker stop nginx-schemas

docker network rm json-schema-validation-network

echo "Failures: $failures"
exit $failures

docker run -d -p 8080:8080 --name nginx-schemas --rm schemas