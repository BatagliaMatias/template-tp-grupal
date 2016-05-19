package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.client.config.ClientState;
import ar.fiuba.tdd.tp.client.network.ClientNetworkFacade;
import ar.fiuba.tdd.tp.shared.Message;
import ar.fiuba.tdd.tp.shared.StandardInputManager;
import ar.fiuba.tdd.tp.shared.actions.ActionsChain;
import ar.fiuba.tdd.tp.shared.actions.FactoryActionsChains;


/**
 * Created by jorlando on 26/04/16.
 */
public class ClientHelper {
    public ClientState gameState = ClientState.WAITING;
    ClientNetworkFacade network = null;

    public boolean isConnected() {
        return this.getGameState().isConnected();
    }

    public void endConnection() {
        this.setGameState(ClientState.ENDED);
        this.getNetwork().endConnection();
    }

    public ClientState getGameState() {
        return this.gameState;
    }

    public void setGameState(ClientState newState) {
        this.gameState = newState;
    }

    public ClientNetworkFacade getNetwork() {
        return this.network;
    }

    public void setNetwork(ClientNetworkFacade newNet) {
        this.network = newNet;
        this.setGameState(ClientState.RUNNING);
    }

    public void sendMessageAndReceive(String messageToSend) {
        network.sendMessage(messageToSend);
        network.messageToStandardOutput(network.receiveMessage());
        if (this.haveToEndConnection()) {
            this.endConnection();
        }
    }

    public void init() {
        StandardInputManager standardInput = new StandardInputManager();
        ActionsChain chain = FactoryActionsChains.clientChain(this);
        System.out.println(Message.INIT_CLIENT.getText());
        while (true) {
            String userCommand = standardInput.read();
            chain.processAction(userCommand);
        }
    }

    public boolean haveToEndConnection() {
        String message = network.getLastMessageReceived();
        return (message.equals(Message.WIN.getText()) || message.equals(Message.LOST.getText()));

    }


}
