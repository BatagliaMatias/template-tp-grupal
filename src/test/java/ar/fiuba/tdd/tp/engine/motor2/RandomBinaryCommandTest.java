package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class RandomBinaryCommandTest {

    @Test
    public void testIsLucky() {
        RandomGeneratorMock rng = new RandomGeneratorMock();
        ArrayList<Double> doubleSequence = new ArrayList<Double>();
        doubleSequence.add(0.8);
        doubleSequence.add(0.7);
        doubleSequence.add(0.1);
        doubleSequence.add(0.4);
        rng.setGetRandomNumberSequence(doubleSequence);
        RandomBinaryCommand randomBinaryCommand = new RandomBinaryCommand("random");
        randomBinaryCommand.setNumberGenerator(rng);

        randomBinaryCommand.setProbability(0.2);
        Assert.assertEquals(randomBinaryCommand.isLucky(), false);
        Assert.assertEquals(randomBinaryCommand.isLucky(), false);
        Assert.assertEquals(randomBinaryCommand.isLucky(), true);
        Assert.assertEquals(randomBinaryCommand.isLucky(), false);
    }

    @Test
    public void testRandomNever() {
        RandomBinaryCommand randomBinaryCommand = new RandomBinaryCommand("never");
        randomBinaryCommand.setExecutableCommand((HashMap<String, Container> components)-> {
                return "Lucky";
            });
        randomBinaryCommand.setProbability(0);
        Assert.assertEquals("Not lucky", randomBinaryCommand.execute());
    }

    @Test
    public void testRandomAlways() {
        RandomBinaryCommand randomBinaryCommand = new RandomBinaryCommand("always");
        randomBinaryCommand.setExecutableCommand((HashMap<String, Container> components)-> {
                return "Lucky";
            });
        randomBinaryCommand.setProbability(1);
        Assert.assertEquals("Lucky", randomBinaryCommand.execute());
    }

    @Test
    public void testRandomNeverCustomCommand() {
        RandomBinaryCommand randomBinaryCommand = new RandomBinaryCommand("never");
        randomBinaryCommand.setExecutableCommand((HashMap<String, Container> components)-> {
                return "Lucky";
            });

        Command notLuckyCommand = new Command("bad luck");
        notLuckyCommand.setExecutableCommand((HashMap<String, Container> components)-> {
                return "Custom bad luck";
            });
        randomBinaryCommand.setProbability(0);
        randomBinaryCommand.setNotLuckyCommand(notLuckyCommand);
        Assert.assertEquals("Custom bad luck", randomBinaryCommand.execute());
    }
}