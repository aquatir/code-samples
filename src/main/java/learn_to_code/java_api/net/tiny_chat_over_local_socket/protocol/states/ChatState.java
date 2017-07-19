package learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states;

import learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream;

/**
 * Proceeding a state should always return another state, which can be the same.
 * Takes {@link learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream} to
 * allow communications between client and server
 */
public abstract class ChatState {

    static String stopString = "STOP";
    public static String getStopString() {
        return stopString;
    }

    ChatStates thisState;

    public ChatState(ChatStates state) {
        thisState = state;
    }

    public ChatStates getEnumType() {
        return thisState;
    }

    abstract public ChatState proceed(ConnectionStream stream);


    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof ChatState)) {
            return false;
        }
        ChatState otherState = (ChatState) obj;
        System.out.println("This type:" + this.getEnumType());
        System.out.println("Other type:" + otherState.getEnumType());

        if (this.getEnumType() != otherState.getEnumType()) // 2 enums can be checked for equity by ==
            return false;
        return true;
    }
}
