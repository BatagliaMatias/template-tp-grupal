package ar.fiuba.tdd.actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorlando on 23/04/16.
 */
public class ActionsChain {

    List<Action> actions;

    public ActionsChain() {
        this.actions = new ArrayList<Action>();
    }

    public void addAction(Action newAction) {
        if ( !this.actions.isEmpty() ) {
            this.getTheLastAction().setNextAction(newAction);
        }
        this.actions.add(newAction);
    }

    private Action getTheLastAction() {
        return this.actions.get(this.actions.size() - 1);
    }

    public void processAction(String userAction) {
        if (!this.actions.isEmpty()) {
            this.actions.get(0).process(userAction);
        }
    }

    public int getSizeActions() {
        return this.actions.size();
    }
}
