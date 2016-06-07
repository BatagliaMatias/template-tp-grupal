package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class RandomCommandTest {
    @Test
    public void testRandomNever() {
        RandomCommand randomCommand = new RandomCommand("never");
        randomCommand.setExecutableCommand((HashMap<String, Container> components)-> {
            return "Lucky";
        });
        randomCommand.setProbability(0);
        Assert.assertEquals("Not lucky",randomCommand.execute());
    }

    @Test
    public void testRandomAlways() {
        RandomCommand randomCommand = new RandomCommand("always");
        randomCommand.setExecutableCommand((HashMap<String, Container> components)-> {
            return "Lucky";
        });
        randomCommand.setProbability(1);
        Assert.assertEquals("Lucky",randomCommand.execute());
    }

    @Test
    public void testRandomNeverCustomCommand() {
        RandomCommand randomCommand = new RandomCommand("never");
        randomCommand.setExecutableCommand((HashMap<String, Container> components)-> {
            return "Lucky";
        });

        Command notLuckyCommand = new Command("bad luck");
        notLuckyCommand.setExecutableCommand((HashMap<String, Container> components)-> {
            return "Custom bad luck";
        });

        randomCommand.setNotLuckyCommand(notLuckyCommand);
        Assert.assertEquals("Custom bad luck",randomCommand.execute());
    }
}