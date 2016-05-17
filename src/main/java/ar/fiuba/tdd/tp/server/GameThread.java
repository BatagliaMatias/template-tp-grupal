package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.engine.motor.*;
import ar.fiuba.tdd.tp.server.exceptions.*;
import ar.fiuba.tdd.tp.server.network.*;
import ar.fiuba.tdd.tp.shared.*;

import java.io.IOException;

/**
 * Created by jorlando on 28/04/16.
 */
public class GameThread extends Thread {

    ServerNetworkFacade network = null;
    ConnectionConfig connectionConfig = null;
    Motor motor = null;
    private String gameName = "";

    public GameThread(String name) {
        super();
        this.gameName = name;
    }

    public void run() {
        try {
            this.init();
        } catch (BadGameNameException e) {
            this.network.messageToStandardOutput(e.getMessage());
            return;
        }
        try {
            this.network.initConnection(this.connectionConfig);
        } catch (IOException e) {
            this.network.messageToStandardOutput(Message.GAME_INIT_ERROR.getText());
            return;
        }
        this.action();
    }

    public void action() {
        String outputLine;
        this.network.sendMessage(this.motor.getWelcomeMessage());
        while (this.network.continuesReceivingMessages()) {
            outputLine = this.motor.processInput(this.network.getLastMessageReceived());
            this.network.sendMessage(outputLine);
            if (outputLine.equals(Message.WIN.getText())) {
                break;
            }
        }
    }

    public void showGameLoaded() {
        StringBuilder message = new StringBuilder();
        message.append(this.gameName);
        message.append(Message.GAME_LOADED.getText());
        message.append(this.connectionConfig.getPort());
        this.network.messageToStandardOutput(message.toString());
    }

    public void init() throws BadGameNameException {
        this.network = new ServerNetworkFacade();
        this.connectionConfig = new ConnectionConfig();
        this.motor = new Motor(this.gameName);
        this.showGameLoaded();
    }
}