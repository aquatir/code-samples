package learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Insulates communication principle between client and server. In theory this should be an interface
 * with implementation for socket streams/http/https/etc communication. For simplicity, this only work
 * with PrintWriter and BufferedReader which can be queried from sockets
 *
 * Note that it implements Closeable/AutoCloseable combo for try-with-resources block
 */
public class ConnectionStream implements Closeable, AutoCloseable {
    private PrintWriter to;
    private BufferedReader from;

    public ConnectionStream(PrintWriter to, BufferedReader from) {
        this.to = to;
        this.from = from;
    }

    public void writeStringTo(String str) {
        to.println(str);
    }

    public String readStringFrom() throws IOException {
        return from.readLine();
    }

    @Override
    public void close() {
        to.close();
        try {
            from.close();
        } catch (IOException e) {
            System.out.println("Could not close connection!");
            e.printStackTrace();
        }
    }
}
