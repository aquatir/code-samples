### About
Deploying nginx and docker with docker compose

### Launching
1. Build spring-boot application image.
    1. Go to project directory
    2. run ```mvn clean package``` to create jar file
    3. run ```docker build -f docker/springboot/Dockerfile . -t spring-boot-hello``` to spring-boot app image
    4. run ```docker-compose -f docker/docker-compose.yml up``` to run nginx and docker