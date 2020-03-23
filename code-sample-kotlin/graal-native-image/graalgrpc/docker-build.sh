#!/bin/sh
rm -rf build
./gradlew assemble
docker build . -t graalgrpc
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run -p 50051:50051 graalgrpc"
