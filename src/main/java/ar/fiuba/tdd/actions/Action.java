package ar.fiuba.tdd.actions;

/**
 * Created by jorlando on 23/04/16.
 */
public abstract class Action {

    Action nextAction;

    public abstract void solve(String userAction);
    public abstract boolean canSolve(String userAction);

    public Action() {
        this.nextAction = null;
    }

    public void setNextAction(Action otherAction) {
        this.nextAction = otherAction;
    }

    public void reSendAction(String userAction) {
        if ( this.nextAction != null ) {
            this.nextAction.process(userAction);
        }
    }

    public void process(String userAction) {
        if ( this.canSolve(userAction) ) {
            this.solve(userAction);
        } else {
            this.reSendAction(userAction);
        }
    }
}
