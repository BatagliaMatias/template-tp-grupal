package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OpenDoor2Test {

    Game game = null;

    @Before
    public void setUp() {
        this.game = (new OpenDoor2()).build();
    }

    @Test
    public void testOpenDoorWithoutKey() {
        assertEquals(this.game.execute("open door"),"Ey! Where do you go?! Room is locked");
    }

    @Test
    public void testPickKeyWithoutOpenBox() {
        assertEquals(this.game.execute("pick key"), "which key?");
    }

    @Test
    public void testOpenBox() {
        assertEquals(this.game.execute("open box"),"The box is open");
    }

    @Test
    public void testWinGame() {
        this.game.execute("open box");
        this.game.execute("pick key");
        this.game.execute("open door");
        assertTrue(this.game.endGame());
        assertEquals(GameState.Won, this.game.getState());
    }

    @Test
    public void testGameInProgress() {
        this.game.execute("open box");
        assertFalse(this.game.endGame());
        assertEquals(GameState.InProgress, this.game.getState());
    }

    @Test
    public void testHelp() {
        assertEquals("HELP: A door locked.. where can you find a key?", this.game.execute("help"));
    }

    @Test
    public void testBuild() {
        Game gameOpenDoor2 = (new OpenDoor2()).build();
        assertEquals(gameOpenDoor2.execute("look at"),"There are Box Door ");
        assertFalse(gameOpenDoor2.endGame());
    }
}

