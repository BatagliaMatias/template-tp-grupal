package ar.fiuba.tdd.actions;

/**
 * Created by jorlando on 26/04/16.
 */

import ar.fiuba.tdd.tp.shared.actions.ActionsEnum;
import ar.fiuba.tdd.tp.shared.actions.SendAction;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class SendActionTest {

    SendAction action = new SendAction();

    @Test
    public void testCanSolve() {
        for (ActionsEnum act : ActionsEnum.values()) {
            assertTrue(action.canSolve(act));
        }
    }
}
