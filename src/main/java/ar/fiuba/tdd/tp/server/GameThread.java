package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameBuilder;
import ar.fiuba.tdd.tp.server.network.*;
import ar.fiuba.tdd.tp.shared.*;
import ar.fiuba.tdd.tp.shared.network.ConnectionConfig;

import java.io.FileNotFoundException;
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
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch (IllegalAccessException | InstantiationException e) {
            System.out.println("ERROR: No se pudo instanciar el GameBuilder ");
            return;
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: cambiar esto
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: No se encontro la clase Main del jar");
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
        this.network.sendMessage(this.getWelcomeMessage());
        while (this.network.continuesReceivingMessages()) {
            outputLine = this.game.execute(this.network.getLastMessageReceived());
            if (this.game.endGame()) {
                this.network.sendMessage(this.game.getFinalMessage());
                break;
            } else {
                this.network.sendMessage(outputLine);
            }
        }
    }

    public void showGameLoaded() {
        StringBuilder message = new StringBuilder();
        message.append(this.getGameNameParsed());
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

    public String getWelcomeMessage() {
        return Message.WELCOME.getText().concat(this.getGameNameParsed());
    }

    public String getGameNameParsed() {
        return this.gameName.replaceAll(".jar", "").replaceAll(".*/","");
    }
}