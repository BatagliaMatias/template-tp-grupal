package ar.fiuba.tdd.actions;

/**
 * Created by jorlando on 26/04/16.
 */
public class ExitAction  extends Action {

    public void solve(String userAction) {
        //TODO: do something, stop connection

    }

    public boolean canSolve(ActionsEnum action) {
        return action.equals(ActionsEnum.EXIT);
    }
}