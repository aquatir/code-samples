package learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol;

import learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.states.ChatState;
import learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.states.ChatStates;
import learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.states.ChatStatesMap;

/**
 * A Protocol determining how communication between server and client should work. Works like a state-machine
 * (Or {@link learn_to_code.patterns.state.State} design pattern) switching from one state to another depending on user input
 *
 */
public class ChatProtocol {

    /* Magical strings used to determine end of each message and end of communication message.
    * This is presumably THE WORST idea you have seen, but I like it in a weird way*/
    private final static String stopMessage = ",v$k@#@i";
    private final static String stopConnection = "Z;[}534";

    public static String getStopMessageString() {
        return stopMessage;
    }

    public static String getStopConnectionString() {
        return stopConnection;
    }

    ChatState currentState;
    ConnectionStream connection;

    public ChatProtocol(ConnectionStream connection) {
        this.connection = connection;
    }

    public void initiateCommunication() {
        currentState = ChatStatesMap.getState(ChatStates.CONNECTION_INITIATED);
        while (!isClientTerminatedState(currentState)) {
            System.out.println("Processing state: " + currentState);
            currentState = proceedState();
        }
        connection.close();
    }

    private boolean isClientTerminatedState(ChatState currentState) {
        return currentState.equals(ChatStatesMap.getState(ChatStates.CLIENT_TERMINATED));
    }
    /**
     * The reason why this method exists, when you can call {@link ChatState#proceed(ConnectionStream)} is because
     * the state doesn't know that connection it's using and protocol do know
     * @return new state (or old state) what will be acquire by server
     */
    public ChatState proceedState() {
        return currentState.proceed(connection);
    }
}
