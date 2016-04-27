package ar.fiuba.tdd.tp.shared.actions;

/**
 * Created by jorlando on 23/04/16.
 */
public class InvalidAction extends Action {

    public void solve(String userAction) {
        System.out.println("Action " + userAction + " not recognized");
    }

    public boolean canSolve(ActionsEnum action) {
        return action.equals(ActionsEnum.INVALID);
    }
}

