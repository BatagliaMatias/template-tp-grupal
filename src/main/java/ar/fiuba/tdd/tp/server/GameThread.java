package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.server.motor.Motor;
import ar.fiuba.tdd.tp.server.network.ServerNetworkFacade;
import ar.fiuba.tdd.tp.shared.ConnectionConfig;
import ar.fiuba.tdd.tp.shared.Message;
import ar.fiuba.tdd.tp.shared.StandardInputManager;

import java.io.IOException;

/**
 * Created by jorlando on 28/04/16.
 */
public class GameThread extends Thread {

    private String gameName = "";
    ServerNetworkFacade network = null;
    ConnectionConfig connectionConfig = null;
    Motor motor = null;

    public GameThread(String name) {
        super();
        this.gameName = name;
    }

    public void run() {
        this.init();
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

    public void init() {
        this.network = new ServerNetworkFacade();
        this.connectionConfig = new ConnectionConfig();
        // Initiate conversation with client
        //Motor motor = new Motor(this.gameName);
        this.motor = new Motor();
        this.showGameLoaded();
    }
}