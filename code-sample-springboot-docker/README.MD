Spring-boot app as docker image.

To launch this image first build it with

````
mvn install dockerfile:build
````

Then run creating image with 
````
docker run -p 8080:8080 -t aquatir/codesample-springboot-docker
````

See pom.xml file to find out how to change image name