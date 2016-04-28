package ar.fiuba.tdd.actions;

import ar.fiuba.tdd.tp.client.ClientHelper;
import ar.fiuba.tdd.tp.shared.actions.ActionsEnum;
import ar.fiuba.tdd.tp.shared.actions.ExitAction;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jorlando on 26/04/16.
 */
public class ExitActionTest {

    ExitAction action = new ExitAction(new ClientHelper());

    @Test
    public void testCanSolveReturnTrue() {
        assertTrue(action.canSolve(ActionsEnum.EXIT));
    }

    @Test
    public void testCanSolveReturnFalse() {
        for (ActionsEnum act : ActionsEnum.values()) {
            if (!(act.equals(ActionsEnum.EXIT))) {
                assertFalse(action.canSolve(act));
            }
        }
    }
}

