package ar.fiuba.tdd.tp.shared.actions;

import ar.fiuba.tdd.tp.client.ClientHelper;

/**
 * Created by jorlando on 26/04/16.
 */
public class FactoryActionsChains {

    public static ActionsChain clientChain(ClientHelper clientHelper) {
        ActionsChain chain = new ActionsChain();
        chain.addAction(new ConnectAction(clientHelper));
        chain.addAction(new ExitAction(clientHelper));
        chain.addAction(new SendAction(clientHelper));
        return  chain;
    }
}
