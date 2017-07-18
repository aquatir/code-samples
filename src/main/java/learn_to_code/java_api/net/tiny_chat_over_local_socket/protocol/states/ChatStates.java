package learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states;

import java.util.Map;

/**
 * Enum shows all possible states where a chat can be. <br>
 * Possible states: <br>
 * <ul>
 * <li>{@code CONNECTION_INITIATED}. Client has just established connection to client  </li>
 * <li>{@code ADDING_NEW_CLIENT}. Client wants to create new user to login with </li>
 * <li>{@code LOGIN_TO_EXISTING_CLIENT}. Client wants to login to existing user </li>
 * <li>{@code LOGGED_IN}. Client successfully logged in </li>
 * <li>{@code ADDING_NEW_CONTACT}. Client is trying to add new contact </li>
 * <li>{@code CONNECTION_TO_CONTACT}. Client is trying to connect to contact </li>
 * <li>{@code CHAT_ESTABLISHED}. Client has established connection to contact </li>
 * <li>{@code TERMINATE_CLIENT}. Client program is needed to be terminated </li>
 * </ul>
 * <br>
 * Possible state transitions:
 * <ul>
 * <li>{@code CONNECTION_INITIATED} -> {@code CONNECTION_INITIATED}, {@code ADDING_NEW_CLIENT}, {@code LOGIN_TO_EXISTING_CLIENT}, {@code LOGGED_IN}, {@code TERMINATE_CLIENT} </li>
 * <li>{@code ADDING_NEW_CLIENT} -> {@code ADDING_NEW_CLIENT}, {@code LOGGED_IN}, {@code CONNECTION_INITIATED}</li>
 * <li>{@code LOGIN_TO_EXISTING_CLIENT} -> {@code LOGIN_TO_EXISTING_CLIENT}, {@code LOGGED_IN}, {@code CONNECTION_INITIATED}</li>
 * <li>{@code LOGGED_IN} -> {@code LOGGED_IN}, {@code ADDING_NEW_CONTACT}, {@code CONNECTION_TO_CONTACT}, {@code CONNECTION_INITIATED}</li>
 * <li>{@code ADDING_NEW_CONTACT} -> {@code ADDING_NEW_CONTACT}, {@code LOGGED_IN}</li>
 * <li>{@code CONNECTION_TO_CONTACT} -> {@code CONNECTION_TO_CONTACT}, {@code CHAT_ESTABLISHED}, {@code LOGGED_IN} </li>
 * <li>{@code CHAT_ESTABLISHED} -> {@code LOGGED_IN}</li>
 * <li>{@code TERMINATE_CLIENT} -> </li>
 * </ul>
 * Almost every state can go to itself. This mainly (but not always happens when an error occur.
 * <br>
 *
 * The inner class provides a map with between enum elements and actual implementations of
 * {@link learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states.ChatState} interface
 */
public enum ChatStates {
    CONNECTION_INITIATED,
    ADDING_NEW_CLIENT,
    LOGIN_TO_EXISTING_CLIENT,
    LOGGED_IN,
    ADDING_NEW_CONTACT,
    CONNECTION_TO_CONTACT,
    CHAT_ESTABLISHED,
    TERMINATE_CLIENT;

    private ChatStatesMap chatMap = new ChatStatesMap();

    public ChatState getState() {
        return chatMap.getState(this);
    }

    private class ChatStatesMap {

        Map<ChatStates, ChatState> states;

        private ChatStatesMap() {
            states.put(ChatStates.CONNECTION_INITIATED, null);
            states.put(ChatStates.ADDING_NEW_CLIENT, null);
            states.put(ChatStates.LOGIN_TO_EXISTING_CLIENT, null);
            states.put(ChatStates.LOGGED_IN, null);
            states.put(ChatStates.ADDING_NEW_CONTACT, null);
            states.put(ChatStates.CONNECTION_TO_CONTACT, null);
            states.put(ChatStates.CHAT_ESTABLISHED, null);
            states.put(ChatStates.TERMINATE_CLIENT, null);
        }
        public ChatState getState(ChatStates state) {
            return states.get(state);
        }
    }
}


