package learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol;

import learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol.states.ChatState;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ChatProtocol {

    PrintWriter to;
    BufferedReader from;

    public ChatProtocol(PrintWriter to, BufferedReader from) {
        this.to = to;
        this.from = from;

    }

    private void goBackToState(ChatState state) {}

    private void proceedState(ChatState state) {
        state.proceedState();
    }
}
