package ar.fiuba.tdd.tp.shared.actions;

import ar.fiuba.tdd.tp.shared.Message;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LoadActionTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    LoadAction action = new LoadAction();

    @Test
    public void testCanSolveReturnTrue() {
        assertTrue(action.canSolve("load"));
    }

    @Test
    public void testCanSolveReturnFalse() {
        assertFalse(action.canSolve("test"));
    }

    @Test
    public void testBadParameterSolve() {

        action.solve("BadParameter");
        assertEquals(outContent.toString().replaceAll("\n", ""), Message.INVALID_COMMAND_FORMAT.getText());

    }

}
