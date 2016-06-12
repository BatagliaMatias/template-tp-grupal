package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jorlando on 18/05/16.
 */
public class StateTest {

    State state = null;

    @Before
    public void setUp() {
        state = new State();
    }

    @Test
    public void testCheckStatusTrue() {
        state.setState("test",true);
        assertTrue(state.checkStatus("test"));
    }

    @Test
    public void testCheckStatusFalse() {
        state.setState("test",false);
        assertFalse(state.checkStatus("test"));
    }

    @Test
    public void testCheckStatusNotInListOfStatus() {
        state.setState("test",false);
        assertFalse(state.checkStatus("invalid"));
    }
}
