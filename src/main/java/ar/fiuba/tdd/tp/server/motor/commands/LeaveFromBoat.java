package ar.fiuba.tdd.tp.server.motor.commands;

import ar.fiuba.tdd.tp.server.motor.entities.Boat;
import ar.fiuba.tdd.tp.server.motor.entities.GameEntity;

public class LeaveFromBoat extends GameCommand {

    private final Boat boat;
    private final GameEntity targetToLeave;

    public LeaveFromBoat(GameEntity targetToLeave, Boat boat) {
        super("leave " + targetToLeave.getName());
        this.targetToLeave = targetToLeave;
        this.boat = boat;
    }

    public String execute() {
        return boat.retrieve(targetToLeave);
    }
}