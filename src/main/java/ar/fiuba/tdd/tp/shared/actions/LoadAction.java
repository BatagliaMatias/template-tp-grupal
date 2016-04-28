package ar.fiuba.tdd.tp.shared.actions;


import ar.fiuba.tdd.tp.server.GameThread;
import ar.fiuba.tdd.tp.shared.Message;

/**
 * Created by jorlando on 23/04/16.
 */
public class LoadAction extends Action {

    private static final int QTY_VALID_PARAMS = 3;

    public void solve(String userAction) {
        String[] connect = userAction.split(" ");
        if ( connect.length == QTY_VALID_PARAMS && connect[1].equals("game") ) {
            (new GameThread(connect[2])).start();
        } else {
            System.out.println(Message.INVALID_COMMAND_FORMAT.getText());
        }
    }

    public boolean canSolve(ActionsEnum action) {
        return action.equals(ActionsEnum.LOAD);
    }
}
