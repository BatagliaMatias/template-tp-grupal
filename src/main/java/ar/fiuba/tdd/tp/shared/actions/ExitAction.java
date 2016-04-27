package ar.fiuba.tdd.tp.shared.actions;

import ar.fiuba.tdd.tp.client.ClientHelper;
import ar.fiuba.tdd.tp.client.config.GameStates;
import ar.fiuba.tdd.tp.shared.Message;

/**
 * Created by jorlando on 26/04/16.
 */
public class ExitAction extends ClientAction {

    public ExitAction(ClientHelper client) {
        super(client);
    }

    public void solve(String userAction) {
        if (this.clientHelper.isConnected()) {
            this.clientHelper.endConnection();
        } else {
            System.out.println(Message.NO_GAME_CONNECTED.getText());
        }
    }

    public boolean canSolve(ActionsEnum action) {
        return action.equals(ActionsEnum.EXIT);
    }
}