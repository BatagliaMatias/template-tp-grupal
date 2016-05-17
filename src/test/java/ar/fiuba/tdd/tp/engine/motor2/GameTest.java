package ar.fiuba.tdd.tp.engine.motor2;

/**
 * Created by jorlando on 17/05/16.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void testExecuteInvalidCommand() {
        Game game = new Game();
        assertEquals(game.execute("test"), "Invalid command: test");
    }
}
