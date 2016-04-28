package ar.fiuba.tdd.tp.server.motor.factories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameEnumTest {

    @Test
    public void getGame() throws Exception {
        String towerOfHanoi = "towerOfHanoi";
        String pickStick = "pickStick";

        FactoryGames factory = GameEnum.getGame(towerOfHanoi);
        Assert.assertEquals(FactoryTowerOfHanoi.class,factory.getClass());

        factory = GameEnum.getGame(pickStick);
        Assert.assertEquals(FactoryPickStick.class,factory.getClass());
    }
}