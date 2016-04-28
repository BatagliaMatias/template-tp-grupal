package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.shared.Message;
import ar.fiuba.tdd.tp.shared.StandardInputManager;
import ar.fiuba.tdd.tp.shared.actions.ActionsChain;
import ar.fiuba.tdd.tp.shared.actions.FactoryActionsChains;

/**
 * Created by jorlando on 28/04/16.
 */
public class ServerHelper {

    public void init() {
        StandardInputManager standardInput = new StandardInputManager();
        ActionsChain chain = FactoryActionsChains.serverChain();
        System.out.println(Message.INIT_SERVER.getText());
        while (true) {
            String userCommand = standardInput.read();
            chain.processAction(userCommand);
        }
    }
}
