package ar.fiuba.tdd.tp.shared.actions;

/**
 * Created by jorlando on 23/04/16.
 */
public abstract class Action {

    Action nextAction;

    public Action() {
        this.nextAction = null;
    }

    public abstract void solve(String userAction);

    public abstract boolean canSolve(String userAction);

    public void setNextAction(Action otherAction) {
        this.nextAction = otherAction;
    }

    public boolean canSolveRequest(String userAction) {
        String[] command = userAction.split(" ");
        return this.canSolve(command[0]);
    }

    public void reSendAction(String userAction) {
        if (this.nextAction != null) {
            this.nextAction.process(userAction);
        } else {
            System.out.println("Action " + userAction + " not recognized");
        }
    }

    public void process(String userAction) {
        if (this.canSolveRequest(userAction)) {
            this.solve(userAction);
        } else {
            this.reSendAction(userAction);
        }
    }
}
