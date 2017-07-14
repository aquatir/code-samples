package learn_to_code.java_api.net.tiny_chat_over_local_socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This server will open port 10000 on your machine (Can be changed obviously), accept MULTIPLE connection
 * at once. When a new connection is established (When call to serverSocket.accept() returns), a thread is started
 * to work with this connection and then the server start listening to the port again.
 * <br>
 * The communication is defined by {@link learn_to_code.java_api.net.local_one_time_one_connection.SimpleNetworkProtocol} class
 * Client can be {@link learn_to_code.java_api.net.local_one_time_one_connection.SingleConnectionLocalClient}
 */
public class LocalChatServer {

    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(10000)) {
        }

        /*
        1. Start

            > Hello
            > I can do stuff
            > 1. To login under existing user print "login"
            > 2. To create new user print "new"

            1.1.

            Login <

            1.1. Login
                 > Your login?
                login <

                > Your password ?
                password <

                SERVER CHECKS IF USER EXISTS

                1.1.1. User exists

                    > Connected successfully!

                    LOGIN SUCCESSFUL

                1.1.2. User does not exist.

                    > Login failure!

                    GO BACK TO 1.


            1.2. New user

                new <
                > New login?
                login <

                > New password ?
                password <

                CHECK IF LOGIN IS AVAILABLE

                1.2.1. Login available

                    SERVER IS CREATING NEW USER IN IT'S DATABASE(?).
                    > New user created

                    GO TO 2.

                1.2.2. Login is not available

                    > Login is not available, please try again

                    GO TO 1.2.

            1.3. ANYTHING else

                [anything which is not 1.1 or 1.2]
                bla-bla-bla <

                > Sorry, I can't understand your request.

                GO TO 1.


        2. Login successful
            > Login successful. What would you like to do next?
            > 1. To check your contact list print "contacts"
            > 2. To add new contacts print "add new"
            > 3. To connect to other online user print "connect %USER_LOGIN%". Note: active users are marked with * after call to contacts.

            ...

            2.1.
                < contacts

                SERVER GENERATES A LIST OF CONTACTS

                > your contacts are following. Contacts with * sign are online right now:
                 bla-bla-bla-bla

                 GO TO 2.

            2.2.
                < add new

                > Insert user's login

                < user's login

                CHECKS IF USER WITH THIS LOGIN EXISTS

                2.2.1. User with login exists

                    ADD USER TO THIS CLIENTS USER BASE

                    > Success! User %USER_NAME% is now your contact

                2.2.2. User with login does not exist

                    > User does not exists.

                    GO TO 2.

             2.3.
                <connect login

                SERVER TREES TO FIND A USER

                2.3.1. Login does not exists

                2.3.2. Login is okay. User is offline

                2.3.3. Login is okay. User is online. Starting a chat
        */
    }
}
