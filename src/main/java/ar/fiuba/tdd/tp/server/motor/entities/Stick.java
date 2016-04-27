package ar.fiuba.tdd.tp.server.motor.entities;


import ar.fiuba.tdd.tp.server.motor.EntityContainer;

public class Stick extends PickableGameEntity{

    private static final String NAME = "stick";

    public Stick(EntityContainer originLocation, EntityContainer destinationLocation) {

        super(NAME, originLocation, destinationLocation);

    }

}
