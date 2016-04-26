package ar.fiuba.tdd.tp;

public class Door extends GameEntity implements Openable, Unlockable {

    private static final String NAME = "door";
    private boolean locked = true;

    public Door() {
        super(NAME);
    }

    @Override
    public String open() {
        if (locked) {
            return ("Ey! Where do you go?! Room 2 is locked.");
        } else {
            return ("You enter room 2!");
        }
    }

    @Override
    public String close() { //TODO
        return null;
    }

    @Override
    public void unlock() {
        this.locked = false;
    }
}
