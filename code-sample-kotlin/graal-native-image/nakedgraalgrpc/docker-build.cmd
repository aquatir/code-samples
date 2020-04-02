@echo on
rmdir /Q /S build
call gradlew.bat build
echo "gradle build successful. Building an image"
call docker build . -t nakedgraalgrpc -m 8g
echo "image build successful"
echo
echo
echo "To run the docker container execute:"
echo "  docker run -p 50051:50051 -p 50052:50052 --net mynet --name app nakedgraalgrpc"
