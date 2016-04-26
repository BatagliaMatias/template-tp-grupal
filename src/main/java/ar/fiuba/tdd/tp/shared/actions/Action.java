package ar.fiuba.tdd.tp.shared.actions;

/**
 * Created by jorlando on 23/04/16.
 */
public abstract class Action {

    Action nextAction;

    public abstract void solve(String userAction);

    public abstract boolean canSolve(ActionsEnum action);

    public Action() {
        this.nextAction = null;
    }

    public void setNextAction(Action otherAction) {
        this.nextAction = otherAction;
    }

    public boolean canSolveRequest(String userAction) {
        return this.canSolve(ActionsEnum.getEnum(userAction));
    }

    public void reSendAction(String userAction) {
        if ( this.nextAction != null ) {
            this.nextAction.process(userAction);
        }
    }

    public void process(String userAction) {
        if ( this.canSolveRequest(userAction) ) {
            this.solve(userAction);
        } else {
            this.reSendAction(userAction);
        }
    }
}
