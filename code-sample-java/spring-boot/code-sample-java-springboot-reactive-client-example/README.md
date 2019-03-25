## Spring async client example

This example shows how to use async WebFlux client to query normal sycnronous tomcat server.

To use this example
1. Launch server from project non-reactive-server (launched on localhost:8081)
2. Launch client from porject reactive-client (laucnhed on localhost:8080)
3. Query server to see if it's working ```curl localhost:8081/value```
4. Query client to launch 1000 async requests from client to server by executing ```curl localhost:8080/``` 





