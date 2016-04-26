package ar.fiuba.tdd.tp.server.motor;

import ar.fiuba.tdd.tp.server.motor.uses.Nameable;

public class Stage implements Nameable {
    private String name;

    public Stage(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
