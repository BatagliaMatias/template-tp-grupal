package ar.fiuba.tdd.tp.shared;

import ar.fiuba.tdd.tp.shared.network.PortSequence;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jorlando on 03/05/16.
 */

public class PortSequenceTest {

    int initialPort = 4444;

    @Test
    public void testGetInstance() {
        PortSequence ps = PortSequence.getInstance();
        PortSequence ps2 = PortSequence.getInstance();
        assertEquals(ps, ps2);
    }

    @Test
    public void testGetPortInitial() {
        PortSequence ps3 = PortSequence.getInstance();
        assertTrue(ps3.getPort() > initialPort);
    }

    @Test
    public void testGetPortIncrement() {
        PortSequence ps4 = PortSequence.getInstance();
        PortSequence ps5 = PortSequence.getInstance();
        assertEquals(ps4.getPort() + 1, ps5.getPort());
    }
}
