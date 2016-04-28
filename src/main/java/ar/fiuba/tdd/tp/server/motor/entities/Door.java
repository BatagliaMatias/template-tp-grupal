package ar.fiuba.tdd.tp.server.motor.entities;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.uses.Openable;
import ar.fiuba.tdd.tp.server.motor.uses.Unlockable;

public class Door extends GameEntity implements Openable, Unlockable {

    //private static final String NAME = "door";
    private boolean locked = true;
    private Player player;
    private Stage destination;

    public Door(String name, Player player, Stage destination) {
        super(name);
        this.player = player;
        this.destination = destination;
    }

    @Override
    public String open() {
        if (locked) {
            return ("Ey! Where do you go?! " + destination.getName() + " is locked.");
        } else {
            player.setlocation(destination);
            return ("You enter " + destination.getName());
        }
    }

    @Override
    public void unlock() {
        this.locked = false;
    }

}
