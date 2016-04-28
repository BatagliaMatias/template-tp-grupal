package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.games.Game;
import ar.fiuba.tdd.tp.server.motor.games.OpenDoor;
import ar.fiuba.tdd.tp.server.motor.games.OpenDoor2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FactoryOpenDoor2Test {
    private FactoryOpenDoor2 factoryOpenDoor2;

    @Before
    public void setUp() throws Exception {
        factoryOpenDoor2 = new FactoryOpenDoor2();
    }

    @Test
    public void create() throws Exception {
        Game game = factoryOpenDoor2.create();
        Assert.assertEquals(OpenDoor2.class,game.getClass());
    }

    @Test
    public void getHelp() throws Exception {
        String help = "A door locked.. where can you find a key?";
        Assert.assertEquals(help,factoryOpenDoor2.getHelp());
    }
}