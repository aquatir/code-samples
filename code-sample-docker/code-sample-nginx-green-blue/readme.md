### About
Creating green-blue deploy with docker and nginx.

We will mount local files to nginx container to be able to realod them for
green-blue deploy. This is not the only way to implement green-blue deploy with nginx and docker.
Refer to other sources (such as this: https://blog.docker.com/2015/04/tips-for-deploying-nginx-official-image-with-docker/) 
for more info.

### Launching
1. Build spring-boot application image.
    1. Go to project directory
    2. use ```mvn clean package``` to create jar file
    3. use ```docker build -f docker/springboot/Dockerfile . -t spring-boot-manual``` to create an image

2. Run spring-boot container with  ```docker run -p 8080:8080 spring-boot-manual```

3. Run nginx container mounting nginx configuration from local directory

```docker build -f docker/nginx/Dockerfile . -t nginx1```
```docker run -p 80:80  nginx1 --```

```docker network create net```