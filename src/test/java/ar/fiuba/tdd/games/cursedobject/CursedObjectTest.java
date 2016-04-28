package ar.fiuba.tdd.games.cursedobject;

import ar.fiuba.tdd.tp.server.motor.games.CursedObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CursedObjectTest {

    @Test
    public void lookAroundTest() {

        CursedObject game = new CursedObject();

        assertEquals(game.processInput("look around"), "There is a key.There is a door1.");

    }

    @Test
    public void talkToThief() {

        CursedObject game = new CursedObject();

        game.processInput("pick key");
        game.processInput("open door1");

        assertEquals(game.processInput("Talk to thief “Hello“"), "Hi!\nThe thief has just stolen your object!\n...");

    }

    @Test
    public void ifTalkToThiefCanOpenDoor2AndWonTheGame() {

        CursedObject game = new CursedObject();

        game.processInput("pick key");
        game.processInput("open door1");
        game.processInput("Talk to thief “Hello“");

        assertEquals(game.processInput("open door2"), "Congratulations!! You won the game!");

    }


}
