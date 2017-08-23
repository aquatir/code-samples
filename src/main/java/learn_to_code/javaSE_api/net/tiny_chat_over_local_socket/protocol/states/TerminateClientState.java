package learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.states;

import learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream;

/**
 * One before the last state a server gets. Used only to print out goodbye message and them proceeds into only
 * other possible state {@code CLIENT_TERMINATED}
 */
public class TerminateClientState extends ChatState {

    private static final String goodBye = "Goodbye!";

    public TerminateClientState(ChatStates state) {
        super(state);
    }

    @Override
    public ChatState proceed(ConnectionStream stream) {
        stream.writeStopConnectionStringTo(goodBye);
        return ChatStatesMap.getState(ChatStates.CLIENT_TERMINATED);
    }
}
