package learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states;

import learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream;

public class TerminateClientState extends ChatState {

    public TerminateClientState(ChatStates state) {
        super(state);
    }

    /* TODO: implement! */
    @Override
    public ChatState proceed(ConnectionStream stream) {

        return null;
    }
}
