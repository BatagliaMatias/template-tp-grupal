package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.exceptions.BadGameNameException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameEnumTest {

    Exception ex = null;

    @Test
    public void testGetGameTowerOfHanoi() throws Exception {
        String towerOfHanoi = "towerOfHanoi";
        FactoryGames factory = GameEnum.getGame(towerOfHanoi);
        Assert.assertEquals(FactoryTowerOfHanoi.class, factory.getClass());
    }

    @Test
    public void testGetGamePickStick() throws Exception {
        String pickStick = "pickStick";
        FactoryGames factory = GameEnum.getGame(pickStick);
        Assert.assertEquals(FactoryPickStick.class, factory.getClass());
    }

    @Test
    public void testGetGameInvalidGame() throws Exception {
        String invalid = "invalid";
        try {
            FactoryGames factory = GameEnum.getGame(invalid);
        } catch (BadGameNameException e) {
            ex = e;
        }
        assertEquals("Game: INVALID not found.", ex.getMessage());
    }

    @Test
    public void testContainsValid() throws Exception {
        String game = "pickStick";
        assertEquals(GameEnum.contains(game), true);
    }

    @Test
    public void testContainsInalid() throws Exception {
        String game = "invalid";
        assertEquals(GameEnum.contains(game), false);
    }

    @Test
    public void testGetFactory() throws Exception {
        assertEquals(GameEnum.OPENDOOR.getFactory().getClass(), FactoryOpenDoor.class);
    }

    @Test
    public void testGetGameEnumPickStick() throws Exception {
        String pickStick = "pickStick";
        GameEnum ge = GameEnum.getGameEnum(pickStick);
        Assert.assertEquals(ge, GameEnum.PICKSTICK);
    }

    @Test
    public void testGetGameEnumInvalidGame() throws Exception {
        String invalid = "invalid";
        try {
            GameEnum.getGameEnum(invalid);
        } catch (BadGameNameException e) {
            ex = e;
        }
        assertEquals("Game: invalid not found.", ex.getMessage());
    }
}