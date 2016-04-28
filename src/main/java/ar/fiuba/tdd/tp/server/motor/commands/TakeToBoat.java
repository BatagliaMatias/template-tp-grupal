package ar.fiuba.tdd.tp.server.motor.commands;

import ar.fiuba.tdd.tp.server.motor.entities.Boat;
import ar.fiuba.tdd.tp.server.motor.entities.GameEntity;

public class TakeToBoat extends BoatCommand {

    public TakeToBoat(GameEntity target, Boat boat) {
        super("take ", target, boat);
    }

    public String execute() {
        return boat.store(targetToLeave);
    }

}