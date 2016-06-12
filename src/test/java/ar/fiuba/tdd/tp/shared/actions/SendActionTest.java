package ar.fiuba.tdd.tp.shared.actions;

import ar.fiuba.tdd.tp.client.ClientHelper;
import ar.fiuba.tdd.tp.shared.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SendActionTest {

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

    SendAction action = new SendAction(new ClientHelper());

    @Test
    public void testCanSolve() {
        assertTrue(action.canSolve("test"));
    }

    @Test
    public void testBadParameterSolve() {
        action.solve("BadParameter");
        assertEquals(outContent.toString().replaceAll("\n", ""), Message.NO_SEND_CONNECTED.getText());
    }
}
