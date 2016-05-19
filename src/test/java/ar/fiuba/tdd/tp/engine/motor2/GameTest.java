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
        game.execute("test");
        assertTrue(game.win());
    }

    @Test
    public void testSetCommandWinFalse() {
        game.setCommandWin(container, "invalid");
        assertFalse(game.win());
    }

    @Test
    public void testGameStateReady() {
        assertEquals( GameState.READY, game.getState());
    }

    @Test
    public void testGameStateInProgress() {
        game.setCommandWin(new Container("test"), "open");
        game.execute("invalid command");
        assertEquals(GameState.IN_PROGRESS, game.getState());
    }

    @Test
    public void testGameStateWin() {
        game.execute("something");
        assertEquals(GameState.WIN, game.getState());
    }
}
