package ar.fiuba.tdd.tp.server.motor.commands;

import ar.fiuba.tdd.tp.server.motor.EntityCompatibilityChecker;
import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;

public class ConditionalCross extends GameCommand {

    private EntityCompatibilityChecker canCrossChecker;
    private Stage destination;
    private Player player;

    public ConditionalCross(Stage destination, Player player, EntityCompatibilityChecker canCrossChecker) {
        super("cross " + destination.getName(), destination.getName());
        this.player = player;
        this.destination = destination;
        this.canCrossChecker = canCrossChecker;
    }

    @Override
    public String execute() {
        Stage location = player.getLocation();

        if (location == destination) {
            return "Your already there!";
        }

        if (!canCrossChecker.isCompatible(location)) {
            return "You cant do that";
        }
        player.setlocation(destination);
        return "Ok";
    }
}
