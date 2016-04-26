package ar.fiuba.tdd.tp.server.motor;

import ar.fiuba.tdd.tp.server.motor.entities.GameEntity;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Stage location;
    private List<GameEntity> inventory = new ArrayList<GameEntity>();

    public void setlocation(Stage location) {
        this.location = location;
    }

    public Stage getLocation() {
        return location;
    }

    public List<GameEntity> getInventory() {
        return inventory;
    }
}
