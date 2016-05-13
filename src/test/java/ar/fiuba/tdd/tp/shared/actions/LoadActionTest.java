package ar.fiuba.tdd.tp.shared.actions;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jorlando on 26/04/16.
 */
public class LoadActionTest {

    LoadAction action = new LoadAction();

    @Test
    public void testCanSolveReturnTrue() {
        assertTrue(action.canSolve("load"));
    }

    @Test
    public void testCanSolveReturnFalse() {
        assertFalse(action.canSolve("test"));
    }
}
