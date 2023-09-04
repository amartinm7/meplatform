### open api
http://localhost:8000/swagger-ui/index.html#/

### NGINX
curl -X GET --location 'http://localhost:8080/api/v1/certification/0191fd94-6ca3-4f2e-aa55-b90961e4db1c' \
--header 'Content-Type: application/json'

### CREATE A NEW CERTIFICATION
curl -X POST --location 'http://localhost:8000/api/v1/certification' \
--header 'Content-Type: application/json' \
--data '{
         "id": "0191fd94-6ca3-4f2e-aa55-b90961e4db1c",
         "certificationName": "CERTIFICATION_NAME",
         "userId": "ccb58458-8d3d-4191-b09c-7eb24994e84c",
         "subscriptionId": "fd7cb91e-e95e-4603-9bfc-402ff5a495ec",
         "status": "ACTIVE",
         "startDate": "2020-08-18T00:00:00Z",
         "endDate": "2020-12-18T00:00:00Z"
       }'
       
### UPDATE A CERTIFICATION
curl -X PUT --location 'http://localhost:8000/api/v1/certification/0191fd94-6ca3-4f2e-aa55-b90961e4db1c' \
--header 'Content-Type: application/json' \
--data '{
         "certificationName": "CERTIFICATION_NAME_X",
         "userId": "ccb58458-8d3d-4191-b09c-7eb24994e84c",
         "subscriptionId": "fd7cb91e-e95e-4603-9bfc-402ff5a495ec",
         "status": "REJECTED",
         "startDate": "2020-08-18T00:00:00Z",
         "endDate": "2020-12-18T00:00:00Z"
       }'       

### DELETE A CERTIFICATION
curl -X DELETE --location 'http://localhost:8000/api/v1/certification/0191fd94-6ca3-4f2e-aa55-b90961e4db1c' \
--header 'Content-Type: application/json'

### GEL ALL CERTIFICATIONS FOR A USER
curl -X GET --location 'http://localhost:8000/api/v1/certification/?userId=ccb58458-8d3d-4191-b09c-7eb24994e84c&page=0&resultsPerPage=10' \
--header 'Content-Type: application/json'

### GEL ALL CERTIFICATIONS FOR A USER
curl -X GET --location 'http://localhost:8000/api/v1/certification/0191fd94-6ca3-4f2e-aa55-b90961e4db1c' \
--header 'Content-Type: application/json'

