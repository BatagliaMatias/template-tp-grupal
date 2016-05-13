package ar.fiuba.tdd.tp.shared.actions;

import ar.fiuba.tdd.tp.client.ClientHelper;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jorlando on 26/04/16.
 */
public class ConnectActionTest {

    ConnectAction action = new ConnectAction(new ClientHelper());

    @Test
    public void testCanSolveReturnTrue() {
        assertTrue(action.canSolve("connect"));
    }

    @Test
    public void testCanSolveReturnFalse() {
        assertFalse(action.canSolve("test"));
    }
}
