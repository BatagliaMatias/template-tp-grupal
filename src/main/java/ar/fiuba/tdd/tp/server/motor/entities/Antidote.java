package ar.fiuba.tdd.tp.server.motor.entities;

import ar.fiuba.tdd.tp.server.motor.EntityContainer;
import ar.fiuba.tdd.tp.server.motor.Player;

public class Antidote extends PickableGameEntity {

    private static final String NAME = "antidote";
    private Player player;

    public Antidote(Player player, EntityContainer originContainer, EntityContainer destinationContainer) {
        super(NAME, originContainer, destinationContainer);
        this.player = player;
    }

    @Override
    public String pick() {
        player.preventPoison();
        return super.pick();
    }
}