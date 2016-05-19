package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.engine.motor2.Game;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by mbataglia on 18/05/16.
 */
public class PickStickTest {

    Game game = null;

    @Before
    public void setUp() {
        this.game = (new PickStick()).build();
    }

    @Test
    public void build() throws Exception {
        assertEquals(this.game.execute("look at"),"There are Stick ");
        assertFalse(this.game.endGame());
    }

    @Test
    public void winGame() {
        this.game.execute("pick stick");
        assertTrue(this.game.endGame());
    }

    @Test
    public void testHelp() {
        assertEquals("HELP: You have to pick that stick over there.", this.game.execute("help"));
    }
}