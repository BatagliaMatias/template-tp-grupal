package ar.fiuba.tdd.tp.server.motor;

/**
 * Created by jorlando on 04/05/16.
 */

import ar.fiuba.tdd.tp.server.exceptions.BadGameNameException;
import ar.fiuba.tdd.tp.shared.Message;

import org.junit.Test;

import static org.junit.Assert.*;

public class MotorTest {

    Exception ex = null;

    @Test
    public void testNewMotorValid() {
        try {
            Motor motor = new Motor("towerOfHanoi");
        } catch (BadGameNameException e) {
            ex = e;
        }
        assertEquals(ex, null);
    }

    @Test
    public void testNewMotorInvalid() {
        try {
            Motor motor = new Motor("invalid");
        } catch (BadGameNameException e) {
            ex = e;
        }
        assertEquals(ex.getMessage(), "Game: INVALID not found.");
    }

    @Test
    public void testWelcomeMessageOfOpenDoor() {
        Motor motor = null;
        String gameName = "OpenDoor";
        try {
            motor = new Motor(gameName);
        } catch (BadGameNameException e) {
            ex = e;
        }
        assertEquals(motor.getWelcomeMessage(), Message.WELCOME.getText().concat(gameName));
    }

    @Test
    public void testProcessInput() {
        Motor motor = null;
        String gameName = "OpenDoor";
        try {
            motor = new Motor(gameName);
        } catch (BadGameNameException e) {
            ex = e;
        }
        String message = motor.processInput("test");
        assertEquals(message, "Invalid command");
    }
}
