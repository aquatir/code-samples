package learn_to_code.javaSE_api.net.tiny_chat_over_local_socket;

import learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.ChatProtocol;
import learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream;

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
 * The communication is defined by {@link ChatProtocol}, which switches states like a state-machine depending on user input.
 * A client for this server is {@link LocalChatClient}. input/output streams are encapsulated in a single class {@link ConnectionStream}
 * which is used both by client and server
 */
public class LocalChatServer {

    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(10000)) {

            ChatClientTask task = new ChatClientTask(serverSocket);

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

    /**
     *
     */
    private static class ChatClientTask implements Runnable {

        ServerSocket serverSocket;
        ChatProtocol serverProtocol;

        public ChatClientTask(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }

        private void acceptConnection() throws IOException {
            System.out.println("Accepting connection");
            Socket clientSocket = serverSocket.accept();
            ConnectionStream serverConnection = new ConnectionStream(
                    new PrintWriter(clientSocket.getOutputStream(), true),
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
            serverProtocol = new ChatProtocol(serverConnection);
            System.out.println("New connection accepted");
        }

        @Override
        public void run() {
            System.out.println("Starting chat tread");
            serverProtocol.initiateCommunication();
        }
    }
}
