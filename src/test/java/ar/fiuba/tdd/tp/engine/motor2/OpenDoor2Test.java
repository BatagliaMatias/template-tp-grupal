package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OpenDoor2Test {

    @Test
    public void openDoorWithoutKey() {
        Game gameOpenDoor2 = (new OpenDoor2()).build();
        assertEquals(gameOpenDoor2.execute("open door"),"Ey! Where do you go?! Room is locked");
    }

    @Test
    public void pickKeyWithoutOpenBox() {
        Game gameOpenDoor2 = (new OpenDoor2()).build();
        assertEquals(gameOpenDoor2.execute("pick key"), "which key?");
    }

    @Test
    public void openBox() {
        Game gameOpenDoor2 = (new OpenDoor2()).build();
        assertEquals(gameOpenDoor2.execute("open box"),"The box is open");
    }

    @Test
    public void winGame() {
        Game gameOpenDoor2 = (new OpenDoor2()).build();
        gameOpenDoor2.execute("open box");
        gameOpenDoor2.execute("pick key");
        gameOpenDoor2.execute("open door");
        assertTrue(gameOpenDoor2.win());
    }
}

