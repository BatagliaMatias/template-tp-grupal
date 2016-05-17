package ar.fiuba.tdd.tp.shared.actions;


import ar.fiuba.tdd.tp.client.ClientHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FactoryActionsChainTest {


    @Test
    public void testChainLength() {

        assertEquals(FactoryActionsChains.serverChain().getSizeActions(),1);

    }

    @Test
    public void testClientChain() {

        assertEquals(FactoryActionsChains.clientChain(new ClientHelper()).getSizeActions(),3);

    }
}
