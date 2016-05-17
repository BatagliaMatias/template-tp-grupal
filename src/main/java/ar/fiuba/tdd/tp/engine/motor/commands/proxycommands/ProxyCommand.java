package ar.fiuba.tdd.tp.engine.motor.commands.proxycommands;

import ar.fiuba.tdd.tp.engine.motor.Player;
import ar.fiuba.tdd.tp.engine.motor.Stage;
import ar.fiuba.tdd.tp.engine.motor.commands.GameCommand;

public class ProxyCommand extends GameCommand {

    protected GameCommand command;
    private Player player;
    private Stage destinationRoom;

    public ProxyCommand(GameCommand command, Player player, Stage destinationRoom) {

        super("", "");

        this.player = player;
        this.destinationRoom = destinationRoom;
        this.command = command;

    }

    @Override
    public String execute() {

        return this.command.execute();

    }

    @Override
    public boolean canProcessRequest(String request) {

        boolean correctMessage = this.command.canProcessRequest(request);

        return correctMessage && this.player.getLocation() == this.destinationRoom;

    }

    @Override
    public String getIdentifier() {

        if (this.player.getLocation() == this.destinationRoom) {

            return this.command.getIdentifier();

        }

        return "";
    }

}
