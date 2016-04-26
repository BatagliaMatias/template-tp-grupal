package ar.fiuba.tdd.actions;

import ar.fiuba.tdd.tp.shared.actions.ActionsEnum;
import ar.fiuba.tdd.tp.shared.actions.HelpAction;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jorlando on 26/04/16.
 */
public class HelpActionTest {

    HelpAction action = new HelpAction();

    @Test
    public void testCanSolveReturnTrue() {
        assertTrue(action.canSolve(ActionsEnum.HELP));
    }

    @Test
    public void testCanSolveReturnFalse() {
        for (ActionsEnum act : ActionsEnum.values()) {
            if (!(act.equals(ActionsEnum.HELP))) {
                assertFalse(action.canSolve(act));
            }
        }
    }
}
