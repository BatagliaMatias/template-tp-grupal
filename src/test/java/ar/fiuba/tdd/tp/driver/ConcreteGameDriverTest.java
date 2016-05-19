package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.engine.motor2.GameState;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jorlando on 19/05/16.
 */
public class ConcreteGameDriverTest {

    @Test
    public void testOpenDoor2() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("OpenDoor2");
        assertEquals(GameState.READY, driver.getGameState());
        driver.sendCommand("open box");
        assertEquals(GameState.IN_PROGRESS, driver.getGameState());
        driver.sendCommand("pick key");
        driver.sendCommand("open door");
        assertEquals(GameState.WIN, driver.getGameState());

    }
}
