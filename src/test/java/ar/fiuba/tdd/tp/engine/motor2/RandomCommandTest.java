package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RandomCommandTest {

    @Test
    public void executeOne() throws Exception {
        RandomCommand randomCommand = new RandomCommand("random");
        Command oneCommand = new Command("one");
        oneCommand.setExecutableCommand((HashMap<String, Container> components)-> {
            return "one";
        });

        randomCommand.addOptionCommand(oneCommand);

        Assert.assertEquals("one",randomCommand.execute());
    }

    @Test
    public void executeMoreOptions() throws Exception {
        RandomCommand randomCommand = new RandomCommand("random");
        Command oneCommand = new Command("one");
        oneCommand.setExecutableCommand((HashMap<String, Container> components)-> {
            return "Command one";
        });

        Command twoCommand = new Command("two");
        twoCommand.setExecutableCommand((HashMap<String, Container> components)-> {
            return "Command two";
        });

        randomCommand.addOptionCommand(oneCommand);
        randomCommand.addOptionCommand(twoCommand);


        Assert.assertTrue(randomCommand.execute().startsWith("Command"));
    }
}