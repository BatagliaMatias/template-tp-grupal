package ar.fiuba.tdd.tp.engine.motor.entities;

import ar.fiuba.tdd.tp.engine.motor.Player;
import ar.fiuba.tdd.tp.engine.motor.Stage;
import ar.fiuba.tdd.tp.engine.motor.uses.Openable;

public class Door extends GameEntity implements Openable {

    protected Player player;
    protected Stage destination;

    public Door(String name, Player player, Stage destination) {
        super(name);
        this.player = player;
        this.destination = destination;
    }

    @Override
    public String open() {
        if (player.isPoisoned()) {
            player.kill();
        }
        Stage newDestination = player.getLocation();
        newDestination.removeEntity(this);
        destination.addEntity(this);

        player.setlocation(destination);
        destination = newDestination;
        return ("You enter " + player.getLocation().getName());
    }
}
