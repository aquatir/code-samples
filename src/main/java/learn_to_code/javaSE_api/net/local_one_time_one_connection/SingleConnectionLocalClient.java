package learn_to_code.javaSE_api.net.local_one_time_one_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Simple client which connects to server (which should already be running, when request is made) and
 * allows you to send data via console. All communications between you and server and written in console.
 * <br>
 * A client starts an infinity loop with server which read data from server, waits for you response and then send at to server.
 * If a 'bye' output it provided from client, it shuts down connection to server
 * <br>
 * Every client request and user response are written to console.
 */
public class SingleConnectionLocalClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 10000);

             PrintWriter toServer =
                     new PrintWriter(socket.getOutputStream(), true);
             BufferedReader fromServer = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));

             BufferedReader clientConsole = new BufferedReader(new InputStreamReader(System.in));
        ) {

            while (true) {

                String serverOutput = fromServer.readLine();
                System.out.println("Server: " + serverOutput);

                System.out.print("Client: ");
                String clientString = clientConsole.readLine();
                toServer.println(clientString);

                if (clientString.toLowerCase().equals("bye")) {
                    String lastServerResponse = fromServer.readLine();
                    System.out.println("Server: " + lastServerResponse);
                    break;
                }
            }
        }
    }
}
