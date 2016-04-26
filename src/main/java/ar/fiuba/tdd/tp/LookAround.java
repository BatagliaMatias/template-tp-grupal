package ar.fiuba.tdd.tp;

import java.util.List;

public class LookAround extends GameCommand {

    private List<GameEntity> entities;

    public LookAround(List<GameEntity> entities) {

        super("look around");
        this.entities = entities;
    }

    public String execute() {
        String result = "";
        if (this.entities.size() == 0) {
            result = "There is nothing around";
        } else {
            for (GameEntity entity : entities) {
                result += " There is a " + entity.getName();
            }
        }
        return result;
    }
}
