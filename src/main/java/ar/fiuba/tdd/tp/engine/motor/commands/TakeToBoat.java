package ar.fiuba.tdd.tp.engine.motor.commands;

import ar.fiuba.tdd.tp.engine.motor.entities.Boat;
import ar.fiuba.tdd.tp.engine.motor.entities.GameEntity;

public class TakeToBoat extends BoatCommand {

    public TakeToBoat(GameEntity target, Boat boat) {
        super("take ", target, boat);
    }

    public String execute() {
        return boat.store(targetToLeave);
    }

}