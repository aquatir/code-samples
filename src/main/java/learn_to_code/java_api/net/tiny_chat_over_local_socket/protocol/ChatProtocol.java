package learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol;

import learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states.ChatState;
import learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states.ChatStates;
import learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states.ChatStatesMap;

public class ChatProtocol {

    ChatState currentState;
    ConnectionStream connection;

    public ChatProtocol(ConnectionStream connection) {
        this.connection = connection;

    }

    public void initiateCommunication() {
        System.out.println("Initiating new communication");
        currentState = ChatStatesMap.getState(ChatStates.CONNECTION_INITIATED);
        System.out.println("Set current state to: " + currentState.toString());
        System.out.println("is it terminate state?: " + !currentState.equals(ChatStatesMap.getState(ChatStates.TERMINATE_CLIENT)));

        while (!currentState.equals(ChatStatesMap.getState(ChatStates.TERMINATE_CLIENT))) {
            System.out.println("Proceeding state " + currentState.toString());
            currentState = proceedState();
        }
        connection.close();
    }

    public ChatState proceedState() {
        return currentState.proceed(connection);
    }
}
