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
        currentState = ChatStates.CONNECTION_INITIATED.getState();

        while (currentState != ChatStates.TERMINATE_CLIENT.getState()) {
            currentState = proceedState();
        }
        connection.close();
    }

    public ChatState proceedState() {
        return currentState.proceed(connection);
    }
}
