package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameBuilder;
import ar.fiuba.tdd.tp.server.network.*;
import ar.fiuba.tdd.tp.shared.*;

import java.io.IOException;

/**
 * Created by jorlando on 28/04/16.
 */
public class GameThread extends Thread {

    ServerNetworkFacade network = null;
    ConnectionConfig connectionConfig = null;
    private String gameName = "";
    Game game = null;

    public GameThread(String name) {
        super();
        this.gameName = name;
    }

    public void run() {
        try {
            this.init();
       // } catch (BadGameNameException e) {
       //     this.network.messageToStandardOutput(e.getMessage());
       //     return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            //TODO: cambiar esto
            return;
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: cambiar esto
            return;
        } catch (InstantiationException e) {
            e.printStackTrace();
            //TODO: cambiar esto
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //TODO: cambiar esto
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
        //TODO: cambiar esto
        //this.network.sendMessage(this.motor.getWelcomeMessage());
        this.network.sendMessage("MENSAJE INICIAL");
        while (this.network.continuesReceivingMessages()) {
            outputLine = this.game.execute(this.network.getLastMessageReceived());
            if (this.game.win()) {
                this.network.sendMessage(Message.WIN.getText());
                break;
            } else {
                this.network.sendMessage(outputLine);
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

    public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        this.network = new ServerNetworkFacade();
        this.connectionConfig = new ConnectionConfig();
        BuilderLoader loader = new BuilderLoader();
        GameBuilder builder = loader.load(this.gameName);
        this.game = builder.build();
        this.showGameLoaded();
    }
}