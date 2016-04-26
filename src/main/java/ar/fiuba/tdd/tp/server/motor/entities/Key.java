package ar.fiuba.tdd.tp.server.motor.entities;

import ar.fiuba.tdd.tp.server.motor.uses.Unlockable;

import java.util.List;

public class Key extends PickableGameEntity {

    private static final String NAME = "key";
    private Unlockable target;

    public Key(Unlockable targetToUnlock, List<GameEntity> originLocation, List<GameEntity> destinationLocation) {
        super(NAME, originLocation, destinationLocation);
        this.target = targetToUnlock;
    }

    @Override
    public String pick() {
        target.unlock();
        return super.pick();
    }
}