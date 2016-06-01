package ar.fiuba.tdd.tp.shared;

import ar.fiuba.tdd.tp.shared.network.ConnectionConfig;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jorlando on 03/05/16.
 */
public class ConnectionConfigTest {

    @Test
    public void testConnectionConfigDefault() {
        ConnectionConfig cc = new ConnectionConfig();
        assertEquals(cc.getPort(),4445);
        assertEquals(cc.getHostName(), "localhost");
    }

    @Test
    public void testConnectionConfigWithParams() {
        ConnectionConfig cc = new ConnectionConfig("testingHost", 1);
        assertEquals(cc.getPort(),1);
        assertEquals(cc.getHostName(), "testingHost");
    }
}
