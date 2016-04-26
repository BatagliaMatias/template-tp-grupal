package ar.fiuba.tdd.tp.server.motor.commands;

public abstract class GameCommand {
    private String identifier;

    protected GameCommand(String identifier) {
        this.identifier = identifier;
    }

    public abstract String execute();

    public String getIdentifier() {
        return identifier;
    }
}