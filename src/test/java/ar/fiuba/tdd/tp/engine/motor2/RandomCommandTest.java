package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class RandomCommandTest {

    @Test
    public void GetRandomOptions() {
        RandomGeneratorMock rng = new RandomGeneratorMock();
        ArrayList<Integer> intSequence = new ArrayList<Integer>();
        intSequence.add(3);
        intSequence.add(1);
        intSequence.add(3);
        rng.setGetRandomIntSequence(intSequence);
        RandomCommand randomCommand = new RandomCommand("random");
        randomCommand.setNumberGenerator(rng);

        Command one = new Command("one");
        Command two = new Command("two");
        Command three = new Command("three");
        Command four = new Command("four");

        randomCommand.addOptionCommand(one);
        randomCommand.addOptionCommand(two);
        randomCommand.addOptionCommand(three);
        randomCommand.addOptionCommand(four);

        Assert.assertEquals(randomCommand.getRandomOption().getName(), "four");
        Assert.assertEquals(randomCommand.getRandomOption().getName(), "two");
        Assert.assertEquals(randomCommand.getRandomOption().getName(), "four");
    }

    @Test
    public void executeOne() throws Exception {
        RandomCommand randomCommand = new RandomCommand("random");
        Command oneCommand = new Command("one");
        oneCommand.setExecutableCommand((HashMap<String, Container> components) -> {
            return "one";
        });

        randomCommand.addOptionCommand(oneCommand);

        Assert.assertEquals("one", randomCommand.execute());
    }

    @Test
    public void executeMoreOptions() throws Exception {
        RandomCommand randomCommand = new RandomCommand("random");
        Command oneCommand = new Command("one");
        oneCommand.setExecutableCommand((HashMap<String, Container> components) -> {
            return "Command one";
        });

        Command twoCommand = new Command("two");
        twoCommand.setExecutableCommand((HashMap<String, Container> components) -> {
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