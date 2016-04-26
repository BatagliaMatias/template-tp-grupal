package ar.fiuba.tdd.tp;

public abstract class GameCommand {
    private String identifier;

    protected GameCommand(String identifier) {
        this.identifier = identifier;
    }

    abstract String execute();

    public String getIdentifier() {
        return identifier;
    }
}