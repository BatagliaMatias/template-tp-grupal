package ar.fiuba.tdd.tp.engine.motor.entities;

import ar.fiuba.tdd.tp.engine.motor.EntityContainer;
import ar.fiuba.tdd.tp.engine.motor.uses.Pickable;

public class PickableGameEntity extends GameEntity implements Pickable {

    /* originContainer: container where this entity is stored
       destinationContainer: container where this entity is moved to after its picked
    */

    private EntityContainer destinationContainer;
    private EntityContainer originContainer;


    protected PickableGameEntity(String name, EntityContainer originLocation, EntityContainer destinationLocation) {
        super(name);
        this.originContainer = originLocation;
        this.destinationContainer = destinationLocation;
    }

    @Override
    public String pick() {
        originContainer.removeEntity(this);
        destinationContainer.addEntity(this);
        return "There you go";
    }
}
