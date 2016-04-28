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
            String msg;
            if (player.isPoisoned()) {
                msg = " and you have been poisoned!";
            } else {
                msg = " and your antidote blocks a dangerous poison!";
            }
            return super.open() + msg;
        }
        return super.open();
    }

}
