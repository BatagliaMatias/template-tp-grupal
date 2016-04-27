package ar.fiuba.tdd.tp.server.motor;

public class Player {

    private Stage location;
    private EntityContainer inventory = new EntityContainer();

    public void setlocation(Stage location) {
        this.location = location;
    }

    public Stage getLocation() {
        return location;
    }

    public EntityContainer getInventory() {
        return inventory;
    }
}
