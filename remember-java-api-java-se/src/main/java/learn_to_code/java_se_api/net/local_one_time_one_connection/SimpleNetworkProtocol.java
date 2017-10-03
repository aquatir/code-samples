package learn_to_code.java_se_api.net.local_one_time_one_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * This class defines communication protocol between client and server.
 * In this example server is answering requests from client with witty (not really) remarks and awesome puns (not really0
 * if it can parse user input, or states, that server does not understand your request.
 * <br>
 * If user provides "bye" input, this protocol ends infinity loop of request/response. After that you can either start it again,
 * or (most likely), call {@link #lastMessage()} method Usually for each server protocol you have
 * mirrored Client protocol, but it in this case we don't.
 * <br>
 * This will only read A SINGLE LINE from client and provide a single line of response. In order to read multiple lines you would
 * need to either know exactly
 */
public class SimpleNetworkProtocol {

    /* Next couple of strings are server responses */
    private String[] helloResponses = {"Heeey, duuuude", "Whatzzaaaaap", "Yo, daug", "Hello"};
    private String[] supResponses = {"I'm good, yoooo", "Fine.", "Not bad", "A little to the left", "Oh gosh, all kinds of stuff!"};
    private String[] jokeResponses = {"I don't know if I just got hit by freezing rain, but it hurt like hail.",
            "Did you hear about the guy whose whole left side was cut off? He's all right now.",
            "Yesterday I accidentally swallowed some food coloring. The doctor says I'm OK, but I feel like I've dyed a little inside.",
            "I wasn't originally going to get a brain transplant, but then I changed my mind.",
            "I'd tell you a chemistry joke but I know I wouldn't get a reaction.",
            "Why don't some couples go to the gym? Because some relationships don't work out.",
            "A friend of mine tried to annoy me with bird puns, but I soon realized that toucan play at that game.",
            "A prisoner's favorite punctuation mark is the period. It marks the end of his sentence.",
            "Why don't programmers like nature? It has too many bugs.",
            "Claustrophobic people are more productive thinking outside the box.",
            "What did the grape say when it got stepped on? Nothing - but it let out a little whine.",
            "The roundest knight at king Arthur's round table was Sir Cumference",
            "I saw a beaver movie last night, it was the best dam movie I've ever seen.",
            "My friend was fired from his job at the road department for stealing. I have to say I saw it coming. The last time I was at his house all the signs were there."};
    private String iCanPrintThis = " 'hey', " + " 'sup', " + " 'joke', " + " 'bye'";
    private String iCanDoThis = "I can answer this requests:" + iCanPrintThis;
    private String iCantDoThis = "Can't parse your input. " + iCanDoThis;
    private String bye = "Bye!";


    private Random rnd = new Random();

    private BufferedReader fromClient;
    private PrintWriter toClient;

    public SimpleNetworkProtocol(BufferedReader fromClient, PrintWriter toClient) {
        this.fromClient = fromClient;
        this.toClient = toClient;
    }

    /**
     * Main loop which proceed data from user. It can only read one line at time. Please don't provide multiple lines
     * from client, because server will go crazy in this case :D
     *
     * @throws java.io.IOException when connection error occurs
     */
    public void proceedData() throws IOException {
        while (true) {
            String nextClientRequest = receiveRequest();
            if (nextClientRequest.toLowerCase().equals("bye")) {
                break;
            }

            String nextServerResponse = parseInputCreateResponse(nextClientRequest);
            toClient.println(nextServerResponse);
        }
    }

    public void initialMessage() {
        sendResponse(iCanDoThis);
    }

    public void lastMessage() {
        sendResponse(bye);
    }

    private String receiveRequest() throws IOException {
        return fromClient.readLine();
    }

    private void sendResponse(String str) {
        toClient.println(str);
    }

    /**
     * Method parses client requests and create responses.
     * Note: this method will not parse 'bye' request, as it should already be handled, before calling this method
     *
     * @param clientInput input from client
     * @return a string representing server response
     */
    private String parseInputCreateResponse(String clientInput) {
        if (clientInput.toLowerCase().equals("hey")) {
            return helloResponse();
        } else if (clientInput.toLowerCase().equals("sup")) {
            return supResponse();
        } else if (clientInput.toLowerCase().equals("joke")) {
            return jokeResponse();
        } else {
            return dontUnderstandResponse();
        }

    }

    private String helloResponse() {
        return helloResponses[rnd.nextInt(helloResponses.length - 1)];
    }

    private String supResponse() {
        return supResponses[rnd.nextInt(supResponses.length - 1)];
    }

    private String jokeResponse() {
        return jokeResponses[rnd.nextInt(jokeResponses.length - 1)];
    }

    private String dontUnderstandResponse() {
        return iCantDoThis;
    }


}
