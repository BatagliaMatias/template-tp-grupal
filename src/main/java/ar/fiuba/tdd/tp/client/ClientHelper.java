package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.client.config.GameStates;
import ar.fiuba.tdd.tp.client.network.ClientNetworkFacade;
import ar.fiuba.tdd.tp.shared.Message;
import ar.fiuba.tdd.tp.shared.actions.ActionsChain;
import ar.fiuba.tdd.tp.shared.actions.FactoryActionsChains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jorlando on 26/04/16.
 */
public class ClientHelper {
    static final String ENCODING = "UTF-8";
    public GameStates gameState = GameStates.WAITING;
    ClientNetworkFacade network = null;

    public boolean isConnected() {
        return this.getGameState().isConnected();
    }

    public void endConnection() {
        this.setGameState(GameStates.ENDED);
        this.getNetwork().endConnection();
    }

    public void setGameState(GameStates newState) {
        this.gameState = newState;
    }

    public GameStates getGameState() {
        return this.gameState;
    }

    public void setNetwork(ClientNetworkFacade newNet) {
        this.network = newNet;
        this.setGameState(GameStates.RUNNING);
    }

    public ClientNetworkFacade getNetwork() {
        return this.network;
    }

    public void sendMessageAndReceive(String messageToSend) {
        network.sendMessage(messageToSend);
        network.messageToStandardOutput(network.getLastMessageReceived());
        if (network.getLastMessageReceived().equals(Message.WIN.getText())) {
            this.endConnection();
        }
    }

    public void play() throws IOException {
        BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in, ENCODING));
        ActionsChain chain = FactoryActionsChains.clientChain(this);
        System.out.println(Message.INIT_CLIENT.getText());
        while (true) {
            String userCommand = standardInput.readLine();
            chain.processAction(userCommand);
        }
    }


}
