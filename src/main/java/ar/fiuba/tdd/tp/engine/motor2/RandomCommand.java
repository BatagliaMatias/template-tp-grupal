package ar.fiuba.tdd.tp.engine.motor2;

import java.util.ArrayList;
import java.util.Random;

public class RandomCommand extends Command {
    private ArrayList<Command> options;
    public RandomCommand(String name) {
        super(name);
        options  = new ArrayList<>();
    }

    public void addOptionCommand(Command option) {
        options.add(option);
    }

    private Command getRandomOption() {
        int optionNumber = new Random().nextInt(options.size());
        return options.get(optionNumber);
    }

    @Override
    public String execute() {
        return getRandomOption().execute();
    }
}
