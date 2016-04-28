package ar.fiuba.tdd.games;

import ar.fiuba.tdd.tp.server.motor.gamemethod.TemplateLoadOpenDoor;
import ar.fiuba.tdd.tp.server.motor.gamemethod.TemplateLoadOpenDoor2;
import ar.fiuba.tdd.tp.server.motor.games.OpenDoor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenDoorTest {

    @Test
    public void wonPickKeyAndOpenDoor() {

        OpenDoor game = new OpenDoor(new TemplateLoadOpenDoor());

        game.processInput("pick key");

        assertEquals(game.processInput("open door"),"YOU WIN THE GAME");

    }

    @Test
    public void wonOpenBoxPickKeyAndOpenDoor() {

        OpenDoor game = new OpenDoor(new TemplateLoadOpenDoor2());

        game.processInput("open box");
        game.processInput("pick key");

        assertEquals(game.processInput("open door"),"YOU WIN THE GAME");

    }

}
