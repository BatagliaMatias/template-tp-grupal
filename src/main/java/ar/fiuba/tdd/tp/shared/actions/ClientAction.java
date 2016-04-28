package ar.fiuba.tdd.tp.shared.actions;

import ar.fiuba.tdd.tp.client.ClientHelper;

/**
 * Created by jorlando on 27/04/16.
 */
public abstract class ClientAction extends Action {

    ClientHelper clientHelper = null;

    public ClientAction(ClientHelper client) {
        super();
        this.clientHelper = client;
    }
}