package learn_to_code.java_api.net.tiny_chat_over_local_socket.protocol;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ChatProtocol {

    ChatState currentState;
    PrintWriter to;
    BufferedReader from;

    public ChatProtocol(PrintWriter to, BufferedReader from) {
        this.to = to;
        this.from = from;
    }

    private void goBackTo(ChatState state) {}

    private void proceedState(ChatState state) {}
}
