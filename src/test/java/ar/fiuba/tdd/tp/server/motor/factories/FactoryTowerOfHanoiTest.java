package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.games.Game;
import ar.fiuba.tdd.tp.server.motor.games.TowerOfHanoi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FactoryTowerOfHanoiTest {
    private FactoryTowerOfHanoi factoryTowerOfHanoi;

    @Before
    public void setUp() throws Exception {
        factoryTowerOfHanoi = new FactoryTowerOfHanoi();
    }

    @Test
    public void create() throws Exception {
        Game game = factoryTowerOfHanoi.create();
        Assert.assertEquals(TowerOfHanoi.class,game.getClass());
    }

    @Test
    public void getHelp() throws Exception {
        String help = "The objective of the puzzle is to move the entire stack to another rod.";
        Assert.assertEquals(help,factoryTowerOfHanoi.getHelp());
    }
}