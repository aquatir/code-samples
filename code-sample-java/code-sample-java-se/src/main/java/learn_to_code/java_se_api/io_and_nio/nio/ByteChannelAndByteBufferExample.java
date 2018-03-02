package learn_to_code.java_se_api.io_and_nio.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ByteChannelAndByteBufferExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = FilesCreatorNIO.createFiles()[0];
        String str = "Hello, world!";

        /* Create write channel to path */
        try (SeekableByteChannel byteChannel = Files.newByteChannel(path, StandardOpenOption.WRITE)) {

            /* Create buffer and put data from string to this buffer. Rewind buffer, because after putting data buffer's pointer is at the end */
            ByteBuffer buffer = ByteBuffer.allocate(str.length());
            buffer.put(str.getBytes());
            buffer.rewind();

            /* Put buffer to channel */
            while (buffer.hasRemaining())
                byteChannel.write(buffer);

            System.out.println("'Hello, world!' should now be written to file");
        }

        /* Create read channel to path*/
        try (SeekableByteChannel byteChannel = Files.newByteChannel(path, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(str.length());

            /* Read data from channel to buffer */
            while (byteChannel.read(buffer) > 0) {
            }

            /* Rewind buffer, because after reading buffer's pointer is at the end */
            buffer.rewind();
            System.out.println("Output from file is: ");

            /* print out data in buffer */
            while (buffer.hasRemaining())
                System.out.print((char)buffer.get());
        }
    }
}
