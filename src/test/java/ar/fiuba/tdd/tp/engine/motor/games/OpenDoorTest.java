package ar.fiuba.tdd.tp.engine.motor.games;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenDoorTest {

    @Test
    public void wonPickKeyAndOpenDoor() {

        OpenDoor game = new OpenDoor();

        game.processInput("pick key");

        assertEquals(game.processInput("open door"),"YOU WIN THE GAME");

    }

}
