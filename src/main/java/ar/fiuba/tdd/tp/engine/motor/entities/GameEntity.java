package ar.fiuba.tdd.tp.engine.motor.entities;

import ar.fiuba.tdd.tp.engine.motor.uses.Nameable;

public abstract class GameEntity implements Nameable {
    private String name;

    protected GameEntity(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}