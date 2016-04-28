package ar.fiuba.tdd.tp.server.motor;

public class Player {

    private Stage location;
    private EntityContainer inventory = new EntityContainer();
    private boolean poisoned = false;
    private boolean dead = false;
    private int poisonsProtections = 0;

    public void kill() {
        dead = true;
    }

    public void setlocation(Stage location) {
        this.location = location;
    }

    public Stage getLocation() {
        return location;
    }

    public EntityContainer getInventory() {
        return inventory;
    }

    public void poison() {
        if (poisonsProtections > 0) {
            poisonsProtections--;
            poisoned = false;
        } else {
            this.poisoned = true;
        }
    }

    public void curePoison() {
        this.poisoned = false;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public boolean isDead() {
        return dead;
    }

    public void preventPoison() {
        if (poisoned) {
            poisoned = false;
        } else {
            this.poisonsProtections++;
        }
    }
}
