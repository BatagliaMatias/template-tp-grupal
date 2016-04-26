package ar.fiuba.tdd.tp.server.motor.commands;

import ar.fiuba.tdd.tp.server.motor.entities.GameEntity;
import ar.fiuba.tdd.tp.server.motor.uses.Pickable;

import java.util.List;

public class Pick extends GameCommand {

    List<GameEntity> locationToPickFrom;
    private Pickable target;

    public Pick(Pickable target, List<GameEntity> locationToPickFrom) {
        super("pick " + target.getName());
        this.target = target;
        this.locationToPickFrom = locationToPickFrom;
    }

    public String execute() {
        if (!locationToPickFrom.contains(target)) {
            return "There is no " + target.getName() + " to pick up here.";
        }
        return target.pick();
    }
}