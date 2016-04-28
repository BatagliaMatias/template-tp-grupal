package ar.fiuba.tdd.tp.shared.actions;

import ar.fiuba.tdd.tp.client.ClientHelper;
import ar.fiuba.tdd.tp.client.network.ClientNetworkFacade;
import ar.fiuba.tdd.tp.shared.ConnectionConfig;
import ar.fiuba.tdd.tp.shared.Message;

import java.io.IOException;

/**
 * Created by jorlando on 26/04/16.
 */
public class ConnectAction extends ClientAction {

    private static final int QTY_VALID_PARAMS = 3;

    public ConnectAction(ClientHelper client) {
        super(client);
    }

    public void solve(String userAction) {
        if (this.clientHelper.isConnected()) {
            System.out.println(Message.ALREADY_GAME_CONNECTED.getText());
        } else {
            this.connect(userAction);
        }
    }

    public void connect(String connection) {
        String[] connect = connection.split(" ");
        if (connect.length == QTY_VALID_PARAMS) {
            ConnectionConfig configNet = new ConnectionConfig(connect[1], Integer.parseInt(connect[2]));
            ClientNetworkFacade network = new ClientNetworkFacade();
            try {
                network.initConnection(configNet);
                this.clientHelper.setNetwork(network);
            } catch (IOException ioe) {
                System.out.println(Message.CONNECT_ERROR.getText());
            }
        } else {
            System.out.println(Message.INVALID_QTY_PARAMS.getText());
        }
    }

    public boolean canSolve(ActionsEnum action) {
        return action.equals(ActionsEnum.CONNECT);
    }
}