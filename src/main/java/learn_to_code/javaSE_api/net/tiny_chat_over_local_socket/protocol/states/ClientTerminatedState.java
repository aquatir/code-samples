package learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.states;

import learn_to_code.javaSE_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream;

/**
 * The last state a server can go to. After this state client connection is dropped. <br>
 * proceed{@link ChatState#proceed(ConnectionStream)} is never called for this state
 */
public class ClientTerminatedState extends ChatState {

    public ClientTerminatedState(ChatStates state) {
        super(state);
    }

    /**
     * This method will never be called
     * @param stream connection stream
     * @return nothing
     */
    @Override
    public ChatState proceed(ConnectionStream stream) {
        throw new IllegalArgumentException("SHOULD NEVER BE CALLED!");
    }
}
