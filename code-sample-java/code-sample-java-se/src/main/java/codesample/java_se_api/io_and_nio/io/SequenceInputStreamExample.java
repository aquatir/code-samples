package codesample.java_se_api.io_and_nio.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;

/** This class takes exactly 2 input streams and forms a single input streams.
* Note that you can combine sequence and other input streams into a new, longer sequence.
*
* The output would also be sequential. The first stream added would be printed first.
* After printing, individual streams will close automatically */
class SequenceInputStreamExample {
    public static void main(String[] args) throws IOException {

        byte[] inp1 = "abcd".getBytes();
        byte[] inp2 = "efgh".getBytes();
        byte[] inp3 = "ijkl".getBytes();

        ByteArrayInputStream input1 = new ByteArrayInputStream(inp1);
        ByteArrayInputStream input2 = new ByteArrayInputStream(inp1);
        ByteArrayInputStream input3 = new ByteArrayInputStream(inp1);

        SequenceInputStream seq1 = new SequenceInputStream(input1, input2);

        try (SequenceInputStream seq2 = new SequenceInputStream(seq1, input3)) {
            int b;
            while ((b = seq2.read()) != -1) {
                System.out.print((char) b + " ");
            }
        }

    }
}
