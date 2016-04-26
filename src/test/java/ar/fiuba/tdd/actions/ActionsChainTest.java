package ar.fiuba.tdd.actions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by jorlando on 26/04/16.
 */
public class ActionsChainTest {

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

    Exception ex = null;

    @Test
    public void testActionsChain() {
        ActionsChain chain = new ActionsChain();
        assertEquals(chain.getSizeActions(), 0);
    }

    @Test
    public void testAddActions() {
        ActionsChain chain = new ActionsChain();
        chain.addAction(new HelpAction());
        chain.addAction(new LoadAction());
        assertEquals(chain.getSizeActions(), 2);
    }

    @Test
    public void testProcessActionWithEmptyListActions() {
        ActionsChain chain = new ActionsChain();
        try {
            chain.processAction("test");
        } catch (IndexOutOfBoundsException e) {
            ex = e;
        }
        assertEquals(null,ex);
    }

    @Test
    public void testProcessActionWithHelpAction() {
        ActionsChain chain = new ActionsChain();
        chain.addAction(new HelpAction());
        try {
            chain.processAction("test");
        } catch (Exception e) {
            ex = e;
        }
        assertEquals(null,ex);
    }

    @Test
    public void testProcessActionWithInvalidAction() {
        ActionsChain chain = new ActionsChain();
        chain.addAction(new HelpAction());
        chain.addAction(new InvalidAction());
        chain.processAction("test");
        assertEquals("Action test not recognized\n".toString(), outContent.toString());
    }

    @Test
    public void testProcessActionWithHelpActionSolved() {
        ActionsChain chain = new ActionsChain();
        chain.addAction(new HelpAction());
        chain.addAction(new InvalidAction());
        chain.processAction("help");
        assertEquals("".toString(), outContent.toString());
    }

}
