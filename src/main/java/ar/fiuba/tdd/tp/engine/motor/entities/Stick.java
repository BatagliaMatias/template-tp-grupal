package ar.fiuba.tdd.tp.engine.motor.entities;


import ar.fiuba.tdd.tp.engine.motor.EntityContainer;

public class Stick extends PickableGameEntity {

    private static final String NAME = "stick";

    public Stick(EntityContainer originLocation, EntityContainer destinationLocation) {

        super(NAME, originLocation, destinationLocation);

    }

}
