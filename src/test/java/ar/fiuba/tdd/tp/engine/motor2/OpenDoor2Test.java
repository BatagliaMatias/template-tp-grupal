package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OpenDoor2Test {

    @Test
    public void testOpenDoorWithoutKey() {
        Game gameOpenDoor2 = (new OpenDoor2()).build();
        assertEquals(gameOpenDoor2.execute("open door"),"Ey! Where do you go?! Room is locked");
    }

    @Test
    public void testPickKeyWithoutOpenBox() {
        Game gameOpenDoor2 = (new OpenDoor2()).build();
        assertEquals(gameOpenDoor2.execute("pick key"), "which key?");
    }

    @Test
    public void testOpenBox() {
        Game gameOpenDoor2 = (new OpenDoor2()).build();
        assertEquals(gameOpenDoor2.execute("open box"),"The box is open");
    }

    @Test
    public void testWinGame() {
        Game gameOpenDoor2 = (new OpenDoor2()).build();
        gameOpenDoor2.execute("open box");
        gameOpenDoor2.execute("pick key");
        gameOpenDoor2.execute("open door");
        assertTrue(gameOpenDoor2.win());
        assertEquals(GameState.WIN, gameOpenDoor2.getState());
    }

    @Test
    public void testGameInProgress() {
        Game gameOpenDoor2 = (new OpenDoor2()).build();
        gameOpenDoor2.execute("open box");
        assertFalse(gameOpenDoor2.win());
        assertEquals(GameState.IN_PROGRESS, gameOpenDoor2.getState());
    }

    @Test
    public void testBuild() {
        Game gameOpenDoor2 = (new OpenDoor2()).build();
        assertEquals(gameOpenDoor2.execute("look at"),"There are Box Door ");
        assertFalse(gameOpenDoor2.win());
    }
}

