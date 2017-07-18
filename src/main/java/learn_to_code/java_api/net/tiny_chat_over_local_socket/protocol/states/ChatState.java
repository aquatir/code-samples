package learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states;

import learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream;

/**
 * Proceeding a state should always return another state, which can be the same.
 * Takes {@link learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.ConnectionStream} to
 * allow communications between client and server
 */
public interface ChatState {
    ChatState proceed(ConnectionStream stream);
}
