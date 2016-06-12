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

    @Test
    public void executeMoreOptionsDesactivate() throws Exception {
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
        randomCommand.desactiveCommand(oneCommand);
        randomCommand.addOptionCommand(twoCommand);


        Assert.assertTrue(randomCommand.execute().equals("Command two"));
    }

    @Test
    public void executeMoreOptionsDesactivateActivate() throws Exception {
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
        randomCommand.desactiveCommand(oneCommand);
        randomCommand.addOptionCommand(twoCommand);
        randomCommand.desactiveCommand(twoCommand);
        randomCommand.activeCommand(twoCommand);


        Assert.assertTrue(randomCommand.execute().equals("Command two"));
    }
}