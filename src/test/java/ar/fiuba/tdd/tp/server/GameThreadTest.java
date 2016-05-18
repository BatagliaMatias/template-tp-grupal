package ar.fiuba.tdd.tp.server;

/**
 * Created by jorlando on 17/05/16.
 */
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameThreadTest {

    @Test
    public void testGetWelcomeMessage() {
        String gameName = "gameTest";
        GameThread gameThread = new GameThread("/directorio/".concat(gameName).concat(".jar"));
        assertEquals(gameThread.getWelcomeMessage(),"Welcome to ".concat(gameName));
    }

    @Test
    public void testGetGameNameParsed() {
        String gameName = "gameTest";
        GameThread gameThread = new GameThread("/directorio/".concat(gameName).concat(".jar"));
        assertEquals(gameThread.getGameNameParsed(),gameName);
    }
}
