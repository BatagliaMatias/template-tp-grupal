package ar.fiuba.tdd.tp.shared;

/**
 * Created by jorlando on 27/04/16.
 */
public enum Message {

    NO_GAME_CONNECTED("You aren't connected to any game"),
    ALREADY_GAME_CONNECTED("You are already connected to a game"),
    CONNECT_PARAMS("Invalid qty of params for connect command"),
    CONNECT_ERROR("Error trying to connect"),
    EXIT_MESSAGE("EXIT"),
    NO_SEND_CONNECTED("Cant send command because you aren't connected to any game"),
    WIN("YES. YOU WIN. THE GAME START AGAIN..."),
    INIT_CLIENT("Client RUNNING! Enter command");

    private final String text;

    Message(String newText) {
        this.text = newText;
    }


    public String getText() {
        return this.text;
    }
}
