package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mbataglia on 18/05/16.
 */
public class PickStickTest {

    @Test
    public void build() throws Exception {
        Game gamePickStick = (new PickStick()).build();
        assertEquals(gamePickStick.execute("look at"),"There are Stick ");
        assertFalse(gamePickStick.win());
    }

    @Test
    public void winGame() {
        Game gamePickStick = (new PickStick()).build();
        gamePickStick.execute("pick stick");
        assertTrue(gamePickStick.win());
    }
}