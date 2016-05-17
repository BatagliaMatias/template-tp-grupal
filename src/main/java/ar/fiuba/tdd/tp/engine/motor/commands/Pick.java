package ar.fiuba.tdd.tp.engine.motor.commands;

import ar.fiuba.tdd.tp.engine.motor.EntityContainer;
import ar.fiuba.tdd.tp.engine.motor.entities.PickableGameEntity;

public class Pick extends GameCommand {

    EntityContainer containerToPickFrom;
    private PickableGameEntity target;

    public Pick(PickableGameEntity target, EntityContainer containerToPickFrom) {
        super("pick " + target.getName(), target.getName());
        this.target = target;
        this.containerToPickFrom = containerToPickFrom;
    }

    public String execute() {
        if (!containerToPickFrom.containsEntity(target)) {
            return "There is no " + target.getName() + " to pick up here.";
        }
        return target.pick();
    }
}