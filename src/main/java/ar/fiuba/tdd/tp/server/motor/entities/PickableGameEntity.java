package ar.fiuba.tdd.tp.server.motor.entities;

import ar.fiuba.tdd.tp.server.motor.uses.Pickable;

import java.util.List;

public class PickableGameEntity extends GameEntity implements Pickable {

    /* originLocation: container where this entity is stored
       destinationLocation: container where this entity is moved to after its picked
    */

    private List<GameEntity> destinationLocation;
    private List<GameEntity> originLocation;


    protected PickableGameEntity(String name, List<GameEntity> originLocation, List<GameEntity> destinationLocation) {
        super(name);
        this.originLocation = originLocation;
        this.destinationLocation = destinationLocation;
    }

    @Override
    public String pick() {
        originLocation.remove(this);
        destinationLocation.add(this);
        return "There you go";
    }
}
