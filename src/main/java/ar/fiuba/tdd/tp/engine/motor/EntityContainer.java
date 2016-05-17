package ar.fiuba.tdd.tp.engine.motor;

import ar.fiuba.tdd.tp.engine.motor.entities.GameEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityContainer {
    protected List<GameEntity> contents = new ArrayList<GameEntity>();

    public void addEntity(GameEntity entity) {
        contents.add(entity);
    }

    public void removeEntity(GameEntity entity) {
        contents.remove(entity);
    }

    public Iterator<GameEntity> getIter() {
        return contents.iterator();
    }

    public int size() {
        return contents.size();
    }

    public boolean containsEntity(GameEntity entity) {
        return this.contents.contains(entity);
    }
}

