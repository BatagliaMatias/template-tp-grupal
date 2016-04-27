package ar.fiuba.tdd.tp.server.motor.commands;

import ar.fiuba.tdd.tp.server.motor.EntityContainer;
import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.entities.GameEntity;

import java.util.Iterator;

public class LookAround extends GameCommand {

    private Player player;

    public LookAround(Player player) {
        super("look around");
        this.player = player;
    }

    public String execute() {
        String result = "";
        EntityContainer target = player.getLocation();

        if (target.size() == 0) {
            result = "There is nothing around";
        } else {
            StringBuilder buf = new StringBuilder();

            Iterator<GameEntity> iter = target.getIter();
            while (iter.hasNext()) {
                GameEntity entity = iter.next();

                buf.append("There is a ");
                buf.append(entity.getName());
                buf.append(".");
            }
            result = buf.toString();
        }
        return result;
    }
}
