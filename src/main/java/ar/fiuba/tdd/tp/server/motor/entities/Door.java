package ar.fiuba.tdd.tp.server.motor.entities;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.uses.Openable;

public class Door extends GameEntity implements Openable {

    protected Player player;
    protected Stage destination;
    private boolean locked = true;

    public Door(String name, Player player, Stage destination) {
        super(name);
        this.player = player;
        this.destination = destination;
    }

    @Override
    public String open() {
        Stage newDestination = player.getLocation();
        newDestination.removeEntity(this);
        destination.addEntity(this);

        player.setlocation(destination);
        destination = newDestination;
        return ("You enter " + player.getLocation().getName());
    }
}
