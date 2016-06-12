package ar.fiuba.tdd.tp.engine.motor2;

public class PlayerCommand {

    public String name;
    PlayerExecutable playerCommand;

    public PlayerCommand(String name) {
        this.name = name;
    }

    public void setPlayerCommand(PlayerExecutable playerCommand) {
        this.playerCommand = playerCommand;
    }

    public String execute(Container player) {
        return this.playerCommand.execute(player);
    }

    public String getName() {
        return name;
    }
}
