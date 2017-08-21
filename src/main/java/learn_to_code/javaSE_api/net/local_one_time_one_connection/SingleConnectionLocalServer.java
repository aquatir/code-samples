package learn_to_code.javaSE_api.net.local_one_time_one_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This server will open port 10000 on your machine (Can be changed obviously), accept a SINGLE connection and then shutdown, when this
 * connection ends.
 * <br>
 * The communication is defined by {@link learn_to_code.javaSE_api.net.local_one_time_one_connection.SimpleNetworkProtocol} class
 */
public class SingleConnectionLocalServer {
    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(10000);

             /* Call to accept() will block until connection is established. Everything after this line is made only when the connection exists */
             Socket clientSocket = serverSocket.accept();
             PrintWriter toClient =
                     new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader fromClient = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()));
        )

        {

            SimpleNetworkProtocol protocol = new SimpleNetworkProtocol(fromClient, toClient);
            protocol.initialMessage();

            /* This call will not return unless user ends connection with 'bye' response */
            protocol.proceedData();

            protocol.lastMessage();
        }
    }
}
