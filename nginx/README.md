# custom nginx

The main goal is to create a docker image of a customized nginx with the scripts to fitter a request.

That is, given a request with a token into the header, let's go to send the token to a lambda or a microservice to validate it. I mean, to validate if the token is the token of an authenticated user.

If the token is valid, nginx receives a 200-OK from the identity service, and send the request to the susbscription microservice

If the token is not valid, nginx receives a 401-no-authorized from the identity service, and send the request to the rquester.