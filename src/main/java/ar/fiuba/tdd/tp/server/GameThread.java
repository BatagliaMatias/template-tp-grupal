package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameBuilder;
import ar.fiuba.tdd.tp.server.network.ServerNetworkFacade;
import ar.fiuba.tdd.tp.shared.Message;
import ar.fiuba.tdd.tp.shared.network.ConnectionConfig;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by jorlando on 28/04/16.
 */
public class GameThread extends Thread {

    ServerNetworkFacade network = new ServerNetworkFacade();
    ConnectionConfig connectionConfig = null;
    Game game = null;
    private String gameName = "";
    private PlayerIDProvider idProvider = new PlayerIDProvider();

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
        this.acceptLoop();
    }

    public void acceptLoop() {
        try {
            network.initConnection(connectionConfig);
            while (true) {
                PlayerConnection player = new PlayerConnection(network.acceptClient(), idProvider, game, getWelcomeMessage());
                System.out.println("Nuevo cliente ");
                player.start();
                game.addPlayer(player);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void action() {
        String outputLine;
        this.network.sendMessage(this.getWelcomeMessage());
        while (this.network.continuesReceivingMessages()) {
            if (this.network.hasMessageToProcess()) {
                outputLine = this.game.execute(this.network.getLastMessageReceived());
                if (this.game.endGame()) {
                    this.network.sendMessage(this.game.getFinalMessage());
                    break;
                } else {
                    this.network.sendMessage(outputLine);
                }
                this.network.cleanMessage();
            }
        }
    }

    public void showGameLoaded() {
        StringBuilder message = new StringBuilder();
        message.append(this.getGameNameParsed());
        message.append(Message.GAME_LOADED.getText());
        message.append(this.connectionConfig.getPort());
        System.out.println(">> Received -> ".concat(message.toString()));
    }

    public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        //this.network = new ServerNetworkFacade();
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
        return this.gameName.replaceAll(".jar", "").replaceAll(".*/", "");
    }
}