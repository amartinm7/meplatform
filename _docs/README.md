# ADR (Architect Document Request)

![architecture_v1.jpg](_img%2Farchitecture_v1.jpg)

### considerations

the nginx single responsibility (Solid) is redirect the traffic, but it can intercept the request to send them to the identity ms, and filter the unauthorized requests

Remember that the MS sends messages to kafka to inform about the status of the domain in their contexts. In this way the communication is ASYNC, but they can communicate in an effective way. No communicate the MS directly, antipattern. Use brokers as Kafka

kubernetes cluster provides us the required security to handle the communication between the MS into the cluster

After the Nginx, all the request are authenticated previously, so we are in a DMZ

###

"show cloud resources" use-case: "invalid JWT token"
1 - The logged user ask for the cloud resources using the app loaded into the browser.

2 - The browser sends a request (JWT token + body payload) to the react frontend server

3 - The react server receives the request on the exposed endpoint for getting the cloud resources, and ask to the facade server (nginx).

4 - The nginx asks to the identity ms if the request contains a valid request: the JWT token is a valid token and is authenticated.

5 - If the token is not valid or the user not authenticated, the identity ms returns a 403 http code(Forbbiden) to say that the request is not valid.

6 - After that nginx receives the response and returns the response to the react server app.

7 - The react server sends the response to the browser and

8 - the user can receive the forbbiden message.

"show cloud resources" use-case: "user with invalid grants"
From the 4 step:

5 - If the token is valid and the user is authenticated, the identity ms returns a 200 http code (OK) or a 204 http code (No-content) to say that request is valid. The nginx server receives the response and keep on with the original request to the susbcription ms.

9 - The subscription ms receives the request and checks if the user is authorized to do the action using the JWT token.

10 - if the user has not grants/rights to do the action, the subscription ms returns a 401 http code (Not authorized) to the nginx server, and from this

11 - returns the 401 to the react server, from here

12 - the react server returns a 401 to the browser so the user can receive the not authorized message.

"show cloud resources" use-case
From the 9 step:

13 - if the user has grants/rights to do the action, then the subcription ms looks for the cloud resources into the database for this user

14 - , and retrieves the response with this information and a 200 http code (Ok) to the nginxs service.

15 - The nginxs response to the react server and from here to the browser, so the user (admin/registered) can read the information.

CQRS - Write part, Extract "resources" from Azure, subscriptions ms
(CQRS: Command Query Responsibility Segregation)

1 - Using a cron service into the Subscription Ms:
2 - The Subscription MS reads the resouces from the azure cloud.

3 - The Subscription MS transform the info read from the azure to our domain cloud resource form.

4 - The Subcription MS  store the cloud resources into the database

5 - Sends a Kafka message to inform that the resource was created



CQRS - Write part, Extract "credentials" from Azure, identity ms
Extract "credentials" from Azure into the identity MS

The same for the use case Extract "credentials" from Azure into the identity MS



"new certificacion" use case (react webhook or consumer)
When the admin creates a new certificacion group, this is the save use case, then "CERT_WAS_SAVED" domain event is fired to send a "NEW_CERTIFICATION" kafka message.

The react server part has a kafka consumer to listen a topic with the "NEW_CERTIFICATION" messages.

When a message is coming, the react server sends the new changes to the browser in the case that the browser was listening the changes in real time. To do that we can use a react websocket.

## JWT token example

![jwt_example.png](_img%2Fjwt_example.png)