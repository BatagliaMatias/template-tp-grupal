package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.server.model.TddGame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTests {

    @Test
    public void dummy() {
        assertEquals(0, 0);
    }

    @Test
    public void initialGameTDD() {
        TddGame myGame = new TddGame();
        String out = myGame.processInput("nothing");
        assertEquals(out,"The name of this Subject is ...");
    }

    @Test
    public void gamingTDD() {
        TddGame myGame = new TddGame();
        String out = myGame.processInput("nothing");
        assertEquals(out,"The name of this Subject is ...");
        String secondOut = myGame.processInput("TDD");
        assertEquals(secondOut,"YES. YOU WIN. THE GAME START AGAIN...");
    }

}
