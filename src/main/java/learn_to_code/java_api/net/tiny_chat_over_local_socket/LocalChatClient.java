package learn_to_code.java_api.net.tiny_chat_over_local_socket;

import learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class LocalChatClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 10000);
             ConnectionStream clientStream = new ConnectionStream(
                     new PrintWriter(socket.getOutputStream(), true),
                     new BufferedReader(new InputStreamReader(socket.getInputStream())));
        ) {


        }
    }
}
