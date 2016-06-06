package ar.fiuba.tdd.tp.shared;

/**
 * Created by jorlando on 27/04/16.
 */
public enum Message {

    NO_GAME_CONNECTED("You aren't connected to any game"),
    ALREADY_GAME_CONNECTED("You are already connected to a game"),
    INVALID_QTY_PARAMS("Invalid qty of params for command"),
    INVALID_COMMAND_FORMAT("Invalid format of command"),
    CONNECT_ERROR("Error trying to connect"),
    GAME_INIT_ERROR("Error trying to load game"),
    EXIT_MESSAGE("EXIT"),
    NO_SEND_CONNECTED("Cant send command because you aren't connected to any game"),
    WIN("WON THE GAME"),
    LOST("LOST THE GAME"),
    WELCOME("Welcome to "),
    INIT_CLIENT("Client RUNNING! Enter command"),
    INIT_SERVER("Server RUNNING! Enter command"),
    INPUT_ERROR("Error trying to read from Standard Input"),
    GAME_LOADED(" loaded and listening on port "),
    BYE("BYE!");

    private final String text;

    Message(String newText) {
        this.text = newText;
    }


    public String getText() {
        return this.text;
    }
}
