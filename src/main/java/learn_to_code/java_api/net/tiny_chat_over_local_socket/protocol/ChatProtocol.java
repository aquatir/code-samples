package learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol;

import learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states.ChatState;
import learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states.ChatStates;

public class ChatProtocol {

    ChatState currentState;
    ConnectionStream connection;

    public ChatProtocol(ConnectionStream connection) {
        this.connection = connection;

    }

    public void initiateCommunication() {
        System.out.println("Initiating new communication");
        currentState = ChatStates.CONNECTION_INITIATED.getState();
        System.out.println("Set current state to: " + currentState.toString());
        System.out.println("is it terminate state?:" + !currentState.equals(ChatStates.TERMINATE_CLIENT.getState()));


        while (!currentState.equals(ChatStates.TERMINATE_CLIENT.getState())) {
            System.out.println("Proceeding state " + currentState.toString());
            currentState = proceedState();
        }
        connection.close();
    }

    public ChatState proceedState() {
        return currentState.proceed(connection);
    }
}
