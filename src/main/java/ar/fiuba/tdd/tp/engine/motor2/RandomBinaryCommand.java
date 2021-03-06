package ar.fiuba.tdd.tp.engine.motor2;

import java.util.HashMap;

public class RandomBinaryCommand extends Command {

    private double probability;
    private Command notLuckyCommand;
    private RandomGenerator rng = new RandomGenerator();

    public RandomBinaryCommand(String name) {
        super(name);
        probability = 0.5;
        notLuckyCommand = new Command("default");
        notLuckyCommand.setExecutableCommand((HashMap<String, Container> components)-> {
                return "Not lucky";
            });

    }

    public void setProbability(double probability) {
        this.probability = probability;
        if (probability > 1) {
            this.probability = 1;
        }
    }

    public void setNotLuckyCommand(Command notLuckyCommand) {
        this.notLuckyCommand = notLuckyCommand;
    }

    public boolean isLucky() {
        return rng.getRandomNumber() <= probability;
    }

    @Override
    public String execute() {
        if (isLucky()) {
            return super.execute();
        }
        return notLuckyCommand.execute();
    }

    public void setNumberGenerator(RandomGenerator rng) {
        this.rng = rng;
    }
}
