package ar.fiuba.tdd.tp.engine.motor.entities;

import ar.fiuba.tdd.tp.engine.motor.EntityContainer;

public class Treasure extends PickableGameEntity {

    public Treasure(EntityContainer originContainer, EntityContainer destinationContainer) {
        super("treasure", originContainer, destinationContainer);
    }
}