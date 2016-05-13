package ar.fiuba.tdd.tp.shared.actions;

/**
 * Created by jorlando on 26/04/16.
 */

import ar.fiuba.tdd.tp.client.ClientHelper;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class SendActionTest {

    SendAction action = new SendAction(new ClientHelper());

    @Test
    public void testCanSolve() {
        assertTrue(action.canSolve("test"));
    }
}
