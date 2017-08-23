package learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol;

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

    public void writeAllTo(String str) {
        writeAllLinesTo(str);
        to.println(ChatProtocol.getStopMessageString());
    }

    public void writeStopConnectionStringTo(String lastMessage) {
        writeAllLinesTo(lastMessage);
        to.println(ChatProtocol.getStopConnectionString());
    }

    private void writeAllLinesTo(String str) {
        for (String line: str.split("\n")) {
            to.print(line+ "\n");
        }
    }

    public String readAllFrom() throws IOException {
        String partOfResponse;
        StringBuilder allResponse = new StringBuilder();

        partOfResponse = from.readLine().trim().replace("\n","");

        while (!isStopString(partOfResponse)) {
            allResponse.append(partOfResponse+"\n");
            partOfResponse = from.readLine().trim().replace("\n","");
        }

        /* delete last trailing break-line symbol */
        allResponse.deleteCharAt(allResponse.lastIndexOf("\n"));

        if (partOfResponse.equals(ChatProtocol.getStopConnectionString())) {
            allResponse.append(ChatProtocol.getStopConnectionString());
        }
        return allResponse.toString();
    }

    private boolean isStopString(String str) {
        return str.equals(ChatProtocol.getStopMessageString()) || str.equals(ChatProtocol.getStopConnectionString());
    }

    @Override
    public void close() {
        to.close();
        try {
            from.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
