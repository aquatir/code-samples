package learn_to_code.javaSE_api.net.local_multiple_times_multiple_connections;

import learn_to_code.javaSE_api.net.local_one_time_one_connection.SimpleNetworkProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This server will open port 10000 on your machine (Can be changed obviously), accept MULTIPLE connection
 * at once. When a new connection is established (When call to serverSocket.accept() returns), a thread is started
 * to work with this connection and then the server start listening to the port again.
 * <br>
 * The communication is defined by {@link learn_to_code.javaSE_api.net.local_one_time_one_connection.SimpleNetworkProtocol} class
 * Client can be {@link learn_to_code.javaSE_api.net.local_one_time_one_connection.SingleConnectionLocalClient}
 */
public class MultipleConnectionsNoShutdownLocalServer {

    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(10000)) {
            while (true) {

                ClientConnectionTask task = new ClientConnectionTask(serverSocket);

                /* This call will block, until first user finishes his requests, after this,
                 * server will return to waiting state and wait for another user.
                 * The only way to shutdown this server is to kill JVM where it is running.
                 * Most of the time, you'll need to implement a better way to shutdown server
                 * something like polling on server-shutdown signal once in a while.
                 *
                 * When the connection get accepted, a task is submitted which return imminently,
                 * so the waiting for next connection begins almost instantly.
                 * The only critical section between tasks is their server socket, which is natural,
                 * because the are all connected to a single source
                 */
                task.acceptConnection();

                executor.submit(task);
            }
        }
    }

    private static class ClientConnectionTask implements Runnable {

        ServerSocket serverSocket;
        Socket clientSocket;
        PrintWriter toClient;
        BufferedReader fromClient;

        public ClientConnectionTask(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }

        private Socket acceptConnection() throws IOException {
            Socket acceptedSocket = serverSocket.accept();
            this.clientSocket = acceptedSocket;
            this.toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            this.fromClient = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            return acceptedSocket;
        }

        private void createAndKeepConnection(ServerSocket serverSocket) throws IOException {

            SimpleNetworkProtocol protocol = new SimpleNetworkProtocol(fromClient, toClient);
            protocol.initialMessage();

            /* This call will not return unless user ends connection with 'bye' response */
            protocol.proceedData();
            protocol.lastMessage();

            /* Closing client socket will also close input and output streams which were opened from this socket */
            clientSocket.close();
        }


        @Override
        public void run() {
            try {
                createAndKeepConnection(this.serverSocket);
            } catch (IOException e) {
                System.out.println("Connection error!");
                e.printStackTrace();
            }
        }
    }
}
