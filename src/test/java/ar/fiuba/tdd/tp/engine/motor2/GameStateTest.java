package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorlando on 19/05/16.
 */
public class GameStateTest {

    @Test
    public void testGetMessageReady() {
        assertEquals("", GameState.Ready.getMessage());
    }

    @Test
    public void testGetMessageInProgress() {
        assertEquals("", GameState.InProgress.getMessage());
    }

    @Test
    public void testGetMessageWon() {
        assertEquals("WON THE GAME", GameState.Won.getMessage());
    }

    @Test
    public void testGetMessageLost() {
        assertEquals("LOST THE GAME", GameState.Lost.getMessage());
    }
}
