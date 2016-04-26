package ar.fiuba.tdd.tp;

import java.util.List;

public class Box extends GameEntity implements Openable, Closable {
    private static final String NAME = "box";
    private List<GameEntity> contents;
    private List<GameEntity> worldEntities;
    private boolean open = false;

    public Box(List<GameEntity> containedEntities, List<GameEntity> worldEntities) {
        super(NAME);
        this.contents = containedEntities;
        this.worldEntities = worldEntities;
    }

    @Override
    public String open() {
        if (open) {
            return "The box is already open!";
        }
        for (GameEntity entity : contents) {
            worldEntities.add(entity);
        }
        open = true;
        return "You have opened the box";
    }

    @Override
    public String close() {
        if (!open) {
            return "The box is already closed!";
        }
        for (GameEntity entity : contents) {
            worldEntities.remove(entity);
        }
        open = false;
        return "You have closed the box";
    }
}
