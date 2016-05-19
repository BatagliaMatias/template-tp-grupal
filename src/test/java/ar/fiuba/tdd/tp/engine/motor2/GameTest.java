package ar.fiuba.tdd.tp.engine.motor2;

/**
 * Created by jorlando on 17/05/16.
 */

import org.junit.*;

import static org.junit.Assert.*;

public class GameTest {

    Game game;
    Container container;

    @Before
    public void setUp() {
        game = new Game();

        State doorState = new State();
        doorState.setState("open",true);
        container = new Container("Door");
        container.setState(doorState);
    }

    @Test
    public void testExecuteInvalidCommand() {
        assertEquals(game.execute("test"), "Invalid command: test");
    }

    @Test
    public void testSetCommandWinTrue() {
        game.setCommandWin(container, "open");
        assertTrue(game.win());
    }

    @Test
    public void testSetCommandWinFalse() {
        game.setCommandWin(container, "invalid");
        assertFalse(game.win());
    }
}
