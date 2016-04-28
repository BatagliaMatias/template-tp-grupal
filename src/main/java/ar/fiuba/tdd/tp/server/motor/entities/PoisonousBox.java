package ar.fiuba.tdd.tp.server.motor.entities;

import ar.fiuba.tdd.tp.server.motor.EntityContainer;
import ar.fiuba.tdd.tp.server.motor.Player;

public class PoisonousBox extends Box {
    private boolean poisonRemaining = true;
    private Player player;

    public PoisonousBox(String name, EntityContainer outsideContainer, Player player) {
        super(name, outsideContainer);
        this.player = player;
    }

    @Override
    public String open() {
        if (poisonRemaining) {
            poisonRemaining = false;
            player.poison();
            return super.open() + " and you have been poisoned!";
        }
        return super.open();
    }

}
