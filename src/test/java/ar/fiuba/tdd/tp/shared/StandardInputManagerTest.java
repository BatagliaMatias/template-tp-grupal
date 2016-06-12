package ar.fiuba.tdd.tp.shared;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by jorlando on 03/05/16.
 */
public class StandardInputManagerTest {

    @Test
    public void testRead() {
        //Simulate standard input
        ByteArrayInputStream in = new ByteArrayInputStream("test".getBytes());
        System.setIn(in);
        StandardInputManager sim = new StandardInputManager();

        String read = sim.read();
        assertEquals(read, "test");
    }

    @Test
    public void testReadAndThrowException() {
        //Simulate standard input
        ByteArrayInputStream in = new ByteArrayInputStream("testing".getBytes());
        System.setIn(in);
        StandardInputManager sim = new StandardInputManager();
        sim.close();

        String read = sim.read();
        assertEquals(read, Message.INPUT_ERROR.getText());
    }
}
