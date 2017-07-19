package learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states;

import learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream;

import java.io.IOException;

public class ConnectionInitiatedState extends ChatState {

    public ConnectionInitiatedState(ChatStates state) {
        super(state);
    }

    @Override
    public ChatState proceed(ConnectionStream stream) {

        System.out.println("Proceeding state. Writing data to user ");
        ChatState newState = ChatStates.CONNECTION_INITIATED.getState();
        stream.writeAllTo("Hello!\n" +
                "Pick option: \n" +
                "'1' to add new user\n" +
                "'2' to login to existing user\n" + "" +
                "'3' to exit and close client");

        System.out.println("Written data to user. Waiting for user output");
        try {
            String dataFromUser = stream.readAllFrom();
            System.out.println("User output is " + dataFromUser + ". Proceeding");
            int userInput = 0;
            if (dataFromUser.length() != 1) {
                stream.writeAllTo("Incorrect Input!\n");
                newState = ChatStates.CONNECTION_INITIATED.getState();
            } else {
                userInput = Integer.parseInt(dataFromUser);
            }

            switch (userInput) {
                case 1:
                    newState = ChatStates.ADDING_NEW_CLIENT.getState();
                case 2:
                    newState = ChatStates.LOGIN_TO_EXISTING_CLIENT.getState();
                case 3:
                    newState = ChatStates.TERMINATE_CLIENT.getState();
                default:
                    newState = ChatStates.CONNECTION_INITIATED.getState(); // Should never happen;
            }

        } catch (IOException e) {
            System.out.println("Client is not available on " + ChatStates.CONNECTION_INITIATED + " state. Closing connection");
            e.printStackTrace();
        }

        return newState;
    }

    @Override
    public String toString() {
        return "CONNECTION_INITIATED state";
    }
}
