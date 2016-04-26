package ar.fiuba.tdd.tp;

public class Key extends GameEntity implements Pickable {

    private static final String NAME = "key";
    private Unlockable target;

    public Key(Unlockable targetToUnlock) {
        super(NAME);
        this.target = targetToUnlock;
    }

    @Override
    public String pick() {
        target.unlock();
        return "There you go!";
    }
}