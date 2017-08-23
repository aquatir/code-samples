package learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.states;

import learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream;

/**
 * Encapsulates basic functionality for any state
 */
public abstract class ChatState {

    ChatStates thisState;

    /**
     * Get enum {@link ChatStates} reference associated with this ChatState. Note that this method is package-private
     * because only classes inside 'states' package should see it
     * @param state enum type from {@link ChatState}
     */
    public ChatState(ChatStates state) {
        thisState = state;
    }

    final ChatStates getEnumType() {
        return thisState;
    }

    /**
     * Initiate a communication with client, until a new state can be determined by user input. Would usually send user
     * a question asking what to do next, read response and then return next state.
     * @param stream connection stream used for client-server communication
     * @return a state which will be applied to server. Returned state can be the same as initial
     */
    abstract public ChatState proceed(ConnectionStream stream);

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof ChatState)) {
            return false;
        }
        ChatState otherState = (ChatState) obj;

        if (this.getEnumType() != otherState.getEnumType()) // 2 enums can be checked for equity by ==
            return false;
        return true;
    }

    @Override
    public String toString() {
        return thisState.name();
    }
}
