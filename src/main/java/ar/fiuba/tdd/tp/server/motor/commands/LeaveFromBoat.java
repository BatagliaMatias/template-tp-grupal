package ar.fiuba.tdd.tp.server.motor.commands;

import ar.fiuba.tdd.tp.server.motor.entities.Boat;
import ar.fiuba.tdd.tp.server.motor.entities.GameEntity;

public class LeaveFromBoat extends BoatCommand {

    public LeaveFromBoat(GameEntity target, Boat boat) {
        super("leave ", target, boat);
    }

    @Override
    public String execute() {
        return boat.retrieve(targetToLeave);
    }

}