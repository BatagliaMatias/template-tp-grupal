package ar.fiuba.tdd.tp.server.motor.commands;

import ar.fiuba.tdd.tp.server.motor.entities.Boat;
import ar.fiuba.tdd.tp.server.motor.entities.GameEntity;

public class TakeToBoat extends GameCommand {

    private GameEntity target;
    private Boat boat;

    public TakeToBoat(GameEntity target, Boat boat) {
        super("take " + target.getName());
        this.target = target;
        this.boat = boat;
    }

    public String execute() {
        return boat.store(target);
    }
}