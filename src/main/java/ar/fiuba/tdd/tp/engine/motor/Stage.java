package ar.fiuba.tdd.tp.engine.motor;

import ar.fiuba.tdd.tp.engine.motor.uses.Nameable;

public class Stage extends EntityContainer implements Nameable {
    private String name;

    public Stage(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
