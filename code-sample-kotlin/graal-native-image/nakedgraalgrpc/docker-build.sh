#!/bin/sh
rm -rf build
./gradlew build
docker build . -t nakedgraalgrpc
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run -p 50051:50051 --net mynet --name app nakedgraalgrpc"
