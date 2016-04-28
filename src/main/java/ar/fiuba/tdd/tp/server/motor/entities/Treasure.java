package ar.fiuba.tdd.tp.server.motor.entities;

import ar.fiuba.tdd.tp.server.motor.EntityContainer;

public class Treasure extends PickableGameEntity {

    public Treasure(EntityContainer originContainer, EntityContainer destinationContainer) {
        super("treasure", originContainer, destinationContainer);
    }
}