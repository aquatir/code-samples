package learn_to_code.java_api.io_and_nio.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileMappingWithFileChannel {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = FilesCreatorNIO.createFiles()[0];
        String str = "Hello, world!";

        /* Creating file channel and opening it for read/write because we can't map to WRITE only operation*/
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(path, StandardOpenOption.READ, StandardOpenOption.WRITE)) {

            /* Mapping file channel to buffer. Note that size of buffer (last parameter) should be sufficient */
            ByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, str.length());

            /* by putting data into buffer we are now putting data into file, because they are mapped on each other */
            buffer.put(str.getBytes());

            System.out.println("'Hello, world!' should now be written to file");
        }

        /* Create file channel again */
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(path)) {

            /* Creating a mapping between file channel and buffer (It's actually MappedByteBuffer class, but we can make references
            * to it as ByteBuffer as it does not implement any additional details.
            *
            * Also, we know that our channel has data in it, so we can call it's size() method to get size needed. */
            ByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

            /* Data is mapped to buffer, so we can simply print it out*/
            System.out.println("File output: ");
            while (buffer.hasRemaining())
                System.out.print((char)buffer.get());
        }
    }
}
