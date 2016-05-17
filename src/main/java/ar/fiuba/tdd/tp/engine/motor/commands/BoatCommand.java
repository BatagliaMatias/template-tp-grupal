package ar.fiuba.tdd.tp.engine.motor.commands;


import ar.fiuba.tdd.tp.engine.motor.entities.Boat;
import ar.fiuba.tdd.tp.engine.motor.entities.GameEntity;

public abstract class BoatCommand extends GameCommand {

    protected Boat boat;
    protected GameEntity targetToLeave;

    public BoatCommand(String identifier, GameEntity targetToLeave, Boat boat) {
        super(identifier + targetToLeave.getName(), targetToLeave.getName());
        this.targetToLeave = targetToLeave;
        this.boat = boat;
    }

}
