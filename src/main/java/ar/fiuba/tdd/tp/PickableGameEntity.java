package ar.fiuba.tdd.tp;

import java.util.List;

public class PickableGameEntity extends GameEntity implements Pickable {

    /* originLocation: container where this entity is stored
       destinationLocation: container where this entity is moved to after its picked
    */

    List<GameEntity> destinationLocation;
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
