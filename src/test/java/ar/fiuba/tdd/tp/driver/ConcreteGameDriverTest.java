package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.engine.motor2.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorlando on 19/05/16.
 */
public class ConcreteGameDriverTest {

    @Test
    public void testOpenDoor2() throws GameLoadFailedException {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("OpenDoor2");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("open box");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("pick key");
        driver.sendCommand("open door");
        assertEquals(GameState.Won, driver.getCurrentState());

    }
    @Test
    public void testGetGame() {
        ConcreteGameDriver driver = new ConcreteGameDriver();
        assertEquals(driver.getGame(),null);

    }
}
