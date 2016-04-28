package ar.fiuba.tdd.tp.server.motor.entities;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.uses.Unlockable;

public class LockedDoor extends Door implements Unlockable {

    private boolean locked = true;

    public LockedDoor(String name, Player player, Stage destination) {
        super(name, player, destination);
    }

    @Override
    public String open() {
        if (locked) {
            return ("Ey! Where do you go?! " + destination.getName() + " is locked.");
        } else {
            return super.open();
        }
    }

    @Override
    public void unlock() {
        this.locked = false;
    }

}
