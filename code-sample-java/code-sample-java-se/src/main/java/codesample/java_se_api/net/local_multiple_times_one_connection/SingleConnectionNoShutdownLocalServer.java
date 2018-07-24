package codesample.java_se_api.net.local_multiple_times_one_connection;


import codesample.java_se_api.net.local_one_time_one_connection.SimpleNetworkProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This server will open port 10000 on your machine (Can be changed obviously), accept a SINGLE connection but do not shutdown
 * after the first connection ends. If multiple clients try to connect simultaneously, they will be blocked,
 * because a client connection cannot proceed unless the server makes a call to accept() method
 * <br>
 * The communication is defined by {@link SimpleNetworkProtocol} class
 * Client can be {@link codesample.java_se_api.net.local_one_time_one_connection.SingleConnectionLocalClient}
 */
public class SingleConnectionNoShutdownLocalServer {
    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(10000)) {
            while (true) {

                /* This call will block, until first user finishes his requests, after this,
                 * server will return to waiting state and wait for another user.
                 * The only way to shutdown this server is to kill JVM where it is running.
                 * Most of the time, you'll need to implement a better way to shutdown server
                 * something like polling on server-shutdown signal once in a while
                 */
                createAndKeepConnection(serverSocket);

            }
        }
    }

    private static void createAndKeepConnection(ServerSocket serverSocket) {
        try (
                Socket clientSocket = serverSocket.accept();
                PrintWriter toClient =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            SimpleNetworkProtocol protocol = new SimpleNetworkProtocol(fromClient, toClient);

            protocol.initialMessage();
            /* This call will not return unless user ends connection with 'bye' response */
            protocol.proceedData();
            protocol.lastMessage();

        } catch (IOException e) {
            System.out.println("Connection error!");
            e.printStackTrace();
        }
    }
}
