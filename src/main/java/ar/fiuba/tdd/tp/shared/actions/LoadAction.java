package ar.fiuba.tdd.tp.shared.actions;

/**
 * Created by jorlando on 23/04/16.
 */
public class LoadAction extends Action {

    public void solve(String userAction) {
        //TODO: do something
    }

    public boolean canSolve(ActionsEnum action) {
        return action.equals(ActionsEnum.LOAD);
    }
}
