### About
Creating green-blue deploy with docker and nginx.

We will mount local files to nginx container to be able to realod them for
green-blue deploy. This is not the only way to implement green-blue deploy with nginx and docker.
Refer to other sources (such as this: https://blog.docker.com/2015/04/tips-for-deploying-nginx-official-image-with-docker/) 
for more info.

### Launching
1. Build spring-boot application image.
    1. Go to project directory
    2. run ```mvn clean package``` to create jar file
    3. run ```docker build -f docker/springboot/Dockerfile . -t spring-boot-hello``` to spring-boot app image
    4. run ```docker-compose -f docker/docker-compose.yml up``` to run nginx and docker