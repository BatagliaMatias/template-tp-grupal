package ar.fiuba.tdd.tp.shared.actions;

import ar.fiuba.tdd.tp.client.ClientHelper;
import ar.fiuba.tdd.tp.shared.Message;

/**
 * Created by jorlando on 26/04/16.
 */
public class SendAction extends ClientAction {

    public SendAction(ClientHelper client) {
        super(client);
    }

    public void solve(String userAction) {
        if (this.clientHelper.isConnected()) {
            this.clientHelper.sendMessage(userAction);
        } else {
            System.out.println(Message.NO_SEND_CONNECTED.getText());
        }
    }

    public boolean canSolve(String action) {
        return true;
    }
}