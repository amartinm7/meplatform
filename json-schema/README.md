# Json Schemas

Repository with the json schema file to validate/create events to send to kafka

## Run

If you want to run your own host with the schemas locally you can do it following these steps:
```bash
docker build -t json-schemas .

docker run --name any-nginx -d -p 80:8080 json-schemas

curl -X GET 'http://localhost:8080/examples/identity/UserCreated-Event.json/1.0.json'
```

Now you can access http://localhost:8080 (or http://192.168.99.100 if you are using docker-machine) and be able to access the schemas

Rebuild everything with the script: 
```bash
sh ./rebuild-docker.sh
```

Use simpleHttpServer in python by running:
```bash 
python -m SimpleHTTPServer 8002

# or 

python3 -m http.server 8002

curl -X GET 'http://localhost:8002/examples/identity/UserCreated-Event.json/1.0.json'
```

Windows users:
```bash 
py -m http.server 8002
```

### Setup host (optional)

Create an alias name on the machine using the /etc/hosts file
```bash
vim /etc/hosts

sublime /etc/hosts
```
something like this:

```
127.0.0.1 any-nginx
```