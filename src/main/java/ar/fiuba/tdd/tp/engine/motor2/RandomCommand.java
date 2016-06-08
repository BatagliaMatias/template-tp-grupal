package ar.fiuba.tdd.tp.engine.motor2;

import java.util.*;
import java.util.stream.Collectors;

public class RandomCommand extends Command {
    private HashMap<Command,Boolean> commands;
    public RandomCommand(String name) {
        super(name);
        commands = new HashMap<>();
    }

    public void addOptionCommand(Command command) {
        commands.put(command,true);
    }

    public void activeCommand(Command command) {
        commands.put(command,true);
    }

    public void desactiveCommand(Command command) {
        commands.put(command,false);
    }
    
    private List<Command> getActiveCommands() {
        return commands.entrySet().stream()
                .filter(entry -> entry.getValue())
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    private Command getRandomOption() {
        int optionNumber = new Random().nextInt(getActiveCommands().size());
        return getActiveCommands().get(optionNumber);
    }

    @Override
    public String execute() {
        return getRandomOption().execute();
    }
}
