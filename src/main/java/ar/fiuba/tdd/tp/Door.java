package ar.fiuba.tdd.tp;

public class Door extends GameEntity implements Openable, Unlockable {

    private static final String NAME = "door";
    private boolean locked = true;
    private Player player;
    private Stage destination;

    public Door(Player player, Stage destination) {
        super(NAME);
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
