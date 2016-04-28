package ar.fiuba.tdd.tp.server.motor.entities;

import ar.fiuba.tdd.tp.server.motor.EntityContainer;
import ar.fiuba.tdd.tp.server.motor.uses.Unlockable;

public class Thief extends GameEntity{

    private static final String NAME = "thief";
    private Unlockable target;
    private EntityContainer originContainer;
    private Key key;


    public Thief(Unlockable target,Key key,EntityContainer originContainer) {

        super(NAME);
        this.target = target;
        this.originContainer = originContainer;
        this.key = key;

    }

    public void steal() {

        this.originContainer.removeEntity(this.key);
        target.unlock();

    }
}
