package ar.fiuba.tdd.tp.engine.motor2;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RandomCommand extends Command {
    private RandomGenerator rng = new RandomGenerator();
    private LinkedHashMap<Command, Boolean> commands;

    public RandomCommand(String name) {
        super(name);
        commands = new LinkedHashMap<>();
    }

    public void addOptionCommand(Command command) {
        commands.put(command, true);
    }

    public void activeCommand(Command command) {
        commands.put(command, true);
    }

    public void desactiveCommand(Command command) {
        commands.put(command, false);
    }

    private List<Command> getActiveCommands() {
        return commands.entrySet().stream()
                .filter(entry -> entry.getValue())
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    public Command getRandomOption() {
        int optionNumber = rng.getRandomInt(getActiveCommands().size());
        return getActiveCommands().get(optionNumber);
    }

    @Override
    public String execute() {
        return getRandomOption().execute();
    }

    public void setNumberGenerator(RandomGenerator rng) {
        this.rng = rng;
    }
}
