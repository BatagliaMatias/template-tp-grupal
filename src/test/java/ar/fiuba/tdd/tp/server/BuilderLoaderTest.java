package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.PickStick;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by mbataglia on 18/05/16.
 */
public class BuilderLoaderTest {

    @Test
    public void build() throws Exception {
        assertEquals(BuilderLoader.getMainClass("./build/libs/PickStick.jar"),"ar.fiuba.tdd.tp.engine.motor2.PickStick");
    }
}