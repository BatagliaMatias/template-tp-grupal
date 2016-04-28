package ar.fiuba.tdd.games;

import ar.fiuba.tdd.tp.server.motor.games.PickStick;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PickStickTest {

    @Test
    public void lookAroundTest() {

        PickStick game = new PickStick();

        assertEquals(game.processInput("look around"), "There is a stick.");

    }

    @Test
    public void ifPickStickThenWonTheGame() {

        PickStick game = new PickStick();

        game.processInput("pick stick");

        assertTrue(game.isGameOver());

    }

}
