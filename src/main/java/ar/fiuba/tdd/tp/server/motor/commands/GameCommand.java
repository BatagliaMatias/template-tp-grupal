package ar.fiuba.tdd.tp.server.motor.commands;

public abstract class GameCommand {
    private String identifier;
    private String targetName;

    protected GameCommand(String identifier, String targetName) {
        this.identifier = identifier;
        this.targetName = targetName;
    }

    public abstract String execute();

    public String getTargetName() {
        return targetName;
    }

    public String getIdentifier() {
        return identifier;
    }


    public boolean canProcessRequest(String request) {
        return this.getIdentifier().equalsIgnoreCase(request);
    }
}