package ar.fiuba.tdd.tp.server.motor.entities;

import ar.fiuba.tdd.tp.server.motor.EntityContainer;
import ar.fiuba.tdd.tp.server.motor.Player;

public class Boat extends GameEntity {
    private static final String NAME = "boat";
    private EntityContainer contents = new EntityContainer();
    private Player player;
    private int capacity;

    public Boat(int capacity, Player player) {
        super(NAME);
        this.capacity = capacity;
        this.player = player;
    }

    public String store(GameEntity entity) {
        EntityContainer outsideContainer = player.getLocation();
        if (!outsideContainer.containsEntity(entity)) {
            return "There is no " + entity.getName();
        }
        if (contents.size() >= capacity) {
            return "You cant do that! The boat is full";
        }
        outsideContainer.removeEntity(entity);
        contents.addEntity(entity);
        return "Ok";
    }

    public String retrieve(GameEntity entity) {
        EntityContainer outsideContainer = player.getLocation();
        if (!contents.containsEntity(entity)) {
            return "There is no " + entity.getName() + " in here.";
        }
        contents.removeEntity(entity);
        outsideContainer.addEntity(entity);
        return "Ok";
    }
}

