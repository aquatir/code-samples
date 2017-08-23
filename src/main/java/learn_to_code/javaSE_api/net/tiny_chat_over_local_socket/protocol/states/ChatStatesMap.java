package learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.states;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Mapping between {@link ChatStates} enum and implementations of {@linkd ChatState}.
 */
public class ChatStatesMap {

    /* There is presumably no reason for this map to be concurrent... */
    private static ConcurrentMap<ChatStates, ChatState> states;

    static {
        /**
         * This map knows how to get object by enum, but objects can also get enums because this enum reference is passed in as constructor argument
         */
        states = new ConcurrentHashMap<>();
        states.put(ChatStates.CONNECTION_INITIATED, new ConnectionInitiatedState(ChatStates.CONNECTION_INITIATED));
       // states.put(ChatStates.ADDING_NEW_CLIENT, null); /* TODO: implement */
       // states.put(ChatStates.LOGIN_TO_EXISTING_CLIENT, null);
       // states.put(ChatStates.LOGGED_IN, null);
       // states.put(ChatStates.ADDING_NEW_CONTACT, null);
       // states.put(ChatStates.CONNECTION_TO_CONTACT, null);
       // states.put(ChatStates.CHAT_ESTABLISHED, null);
        states.put(ChatStates.TERMINATE_CLIENT, new TerminateClientState(ChatStates.TERMINATE_CLIENT));
    }

    private ChatStatesMap() {
    }

    public static ChatState getState(ChatStates state) {
        return states.get(state);
    }
}

