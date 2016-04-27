package ar.fiuba.tdd.tp.server.motor.entities;

import ar.fiuba.tdd.tp.server.motor.EntityContainer;
import ar.fiuba.tdd.tp.server.motor.uses.Closable;
import ar.fiuba.tdd.tp.server.motor.uses.Openable;

import java.util.Iterator;

public class Box extends GameEntity implements Openable, Closable {
    private static final String NAME = "box";
    private EntityContainer contents = new EntityContainer();
    private EntityContainer outsideContainer;
    private boolean open = false;

    public Box(EntityContainer outsideContainer) {
        super(NAME);
        this.outsideContainer = outsideContainer;
    }

    public void add(GameEntity entity) {
        contents.addEntity(entity);
    }

    @Override
    public String open() {
        if (open) {
            return "The box is already open!";
        }

        Iterator<GameEntity> iter = contents.getIter();
        while (iter.hasNext()) {
            GameEntity entity = iter.next();
            outsideContainer.addEntity(entity);
        }
        open = true;
        return "You have opened the box";
    }

    @Override
    public String close() {
        if (!open) {
            return "The box is already closed!";
        }

        Iterator<GameEntity> iter = contents.getIter();
        while (iter.hasNext()) {
            GameEntity entity = iter.next();
            if (outsideContainer.containsEntity(entity)) {
                outsideContainer.removeEntity(entity);
            } else { // si no esta en el exterior quiere decir que la saco el jugador, entonces la borramos de las contenidas
                iter.remove();
            }
        }
        open = false;
        return "You have closed the box";
    }

}
