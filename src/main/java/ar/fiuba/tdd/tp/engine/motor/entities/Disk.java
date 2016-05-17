package ar.fiuba.tdd.tp.engine.motor.entities;


public class Disk extends GameEntity {

    private static final String NAME = "disk";
    private int size;

    public Disk(int size) {

        super(NAME);

        this.size = size;

    }

    public int getSize() {

        return this.size;

    }

}
