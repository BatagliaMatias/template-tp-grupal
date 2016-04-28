package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.games.Game;
import ar.fiuba.tdd.tp.server.motor.games.PickStick;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FactoryPickStickTest {
    private FactoryPickStick factoryPickStick;

    @Before
    public void setUp() throws Exception {
        factoryPickStick = new FactoryPickStick();
    }

    @Test
    public void create() throws Exception {
        Game game = factoryPickStick.create();
        Assert.assertEquals(PickStick.class, game.getClass());
    }

    @Test
    public void getHelp() throws Exception {
        String help = "You have to pick that stick over there.";
        Assert.assertEquals(help, factoryPickStick.getHelp());
    }
}