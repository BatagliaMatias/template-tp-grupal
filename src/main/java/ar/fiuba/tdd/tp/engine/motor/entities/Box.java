package ar.fiuba.tdd.tp.engine.motor.entities;

import ar.fiuba.tdd.tp.engine.motor.EntityContainer;
import ar.fiuba.tdd.tp.engine.motor.uses.Closable;
import ar.fiuba.tdd.tp.engine.motor.uses.Openable;

import java.util.Iterator;

public class Box extends GameEntity implements Openable, Closable {
    private EntityContainer contents = new EntityContainer();
    private EntityContainer outsideContainer;
    private boolean open = false;

    public Box(String name, EntityContainer outsideContainer) {
        super(name);
        this.outsideContainer = outsideContainer;
    }

    public void add(GameEntity entity) {
        contents.addEntity(entity);
    }

    @Override
    public String open() {
        if (open) {
            return this.getName() + " is already open!";
        }

        Iterator<GameEntity> iter = contents.getIter();
        while (iter.hasNext()) {
            GameEntity entity = iter.next();
            outsideContainer.addEntity(entity);
        }
        open = true;
        return "You have opened " + this.getName();
    }

    @Override
    public String close() {
        if (!open) {
            return this.getName() + " is already closed!";
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
