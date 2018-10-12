### About
Deploying nginx and docker with docker compose

### Launching
1. Go to project directory
2. run ```mvn clean package``` to create jar file
3. run ```docker build -f docker/springboot/Dockerfile . -t spring-boot-hello``` to spring-boot app image
4. run ```docker-compose -f docker/docker-compose.yml up``` to run nginx and docker
5. run ``` curl http://localhost/hello``` to call nginx. Nginx will route this call to spring-boot