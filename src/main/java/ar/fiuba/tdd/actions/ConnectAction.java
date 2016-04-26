package ar.fiuba.tdd.actions;

/**
 * Created by jorlando on 26/04/16.
 */
public class ConnectAction extends Action {

    public void solve(String userAction) {
        //TODO: do something, start connection

    }

    public boolean canSolve(ActionsEnum action) {
        return action.equals(ActionsEnum.CONNECT);
    }
}