package learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.states;

import learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream;

import java.io.IOException;

/**
 * First state acquired by server, when connection to client is established.
 * Can proceed for one of the following states:
 * <ul>
 *     <li>{@code CONNECTION_INITIATED}. When user enters input which cannot be parsed correctly</li>
 *     <li>{@code ADDING_NEW_CLIENT}. When user provides </li>
 *     <li>{@code LOGIN_TO_EXISTING_CLIENT}</li>
 *     <li>{@code LOGGED_IN}</li>
 *     <li>{@code TERMINATE_CLIENT}</li>
 * </ul>
 */
public class ConnectionInitiatedState extends ChatState {

    public ConnectionInitiatedState(ChatStates state) {
        super(state);
    }

    @Override
    public ChatState proceed(ConnectionStream stream) {

        System.out.println("Proceeding state. Writing data to user ");

        ChatState newState = ChatStatesMap.getState(ChatStates.CONNECTION_INITIATED);
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
                newState = ChatStatesMap.getState(ChatStates.CONNECTION_INITIATED);
            } else {
                userInput = Integer.parseInt(dataFromUser);
            }

            switch (userInput) {
                case 1:
                    newState = ChatStatesMap.getState(ChatStates.ADDING_NEW_CLIENT);
                    break;
                case 2:
                    newState = ChatStatesMap.getState(ChatStates.LOGIN_TO_EXISTING_CLIENT);
                    break;
                case 3:
                    newState = ChatStatesMap.getState(ChatStates.TERMINATE_CLIENT);
                    break;
                default:
                    newState = ChatStatesMap.getState(ChatStates.CONNECTION_INITIATED); // Should never happen;
            }

        } catch (IOException e) {
            System.out.println("Client is not available on " + ChatStates.CONNECTION_INITIATED + " state. Closing connection");
            e.printStackTrace();
        }

        System.out.println("New state is " + newState);
        return newState;
    }
}
