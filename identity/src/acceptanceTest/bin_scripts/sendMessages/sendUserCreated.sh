#!/bin_scripts/bash

TOPIC="pub.myapp.ms-app--identity.user"

generate_random_uuid() {
  uuidgen
}

for key in {1..1000}
do
  uuid=$(generate_random_uuid)
  printf "Send message: $key\n"
  json_message='{"actor":{"@id":"1231368252"},"published":"2018-11-14T10:06:44.072Z","provider":{"@id":"identity-provider","channel":"desktop"},"schema":"http://localhost:8080/events/identity/UserCreated-Event.json/1.0.json","action":"Create","@id":"'"$uuid"'","@type":"AdPosted","user":{"@id":"7ff764a8-2437-42a0-be57-0f6c20393932","roles":[{"@id":"7ff764a8-2437-42a0-be57-0f6c20393932","name":"Regular-User"},{"@id":"7ff764a8-2437-42a0-be57-0f6c20393932","name":"Admin"}],"contact":{"first_name":"John","last_name_1":"Smith","last_name_2":"Smith","first_phone_number":"54123123123","second_phone_number":"","email":"anyemail@gmail.com"},"department":[{"@id":"7ff764a8-2437-42a0-be57-0f6c20393932","name":"finance","term_duration":{"beginAt":"2014-09-01T03:42:40Z","endsAt":"2014-09-01T03:42:40Z"},"at_present":"false"},{"@id":"7ff764a8-2437-42a0-be57-0f6c20393932","name":"sales","term_duration":{"beginAt":"2014-09-01T03:42:40Z","endsAt":"2014-09-01T03:42:40Z"},"at_present":"true"}],"location":{"geolocation":{"latitude":40.28346,"longitude":-3.79574},"province":{"@id":28,"name":"Madrid"},"locality":{"@id":23020,"name":"Fuenlabrada"},"zipcode":"25000","country":{"@id":1,"name":"Spain"}},"photos":[{"@id":"136825236","order":1,"versionId":"dfggdfgddfsdff.gdfgdsdsfsfdfgdf fdgdfgfhgfhgf"},{"@id":"136825236","order":2,"versionId":"dfggdfgdf.gdfgdfgdf ghfhgfhgf"}],"audit":{"updatedAt":"2014-09-01T03:42:40Z","createdAt":"2014-09-01T03:42:40Z"}}}'
  printf "$json_message\n"
  # -K: expecify that the key is what is before of ':', on the left. On the right is the payload, the json message
  echo "$uuid:$json_message" | kcat -b localhost:9092 -t "$TOPIC" -Z -K:
  # sleep 3
done
