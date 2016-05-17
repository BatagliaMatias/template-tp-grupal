package ar.fiuba.tdd.tp.engine.motor;


import ar.fiuba.tdd.tp.engine.motor.entities.GameEntity;

public class EntityPair {
    GameEntity entityA;
    GameEntity entityB;

    public EntityPair(GameEntity entityA, GameEntity entityB) {
        this.entityA = entityA;
        this.entityB = entityB;
    }

    public boolean equals(GameEntity someEntity, GameEntity otherEntity) {
        return ((entityA == someEntity) && (entityB == otherEntity)) || ((entityB == someEntity) && (entityA == otherEntity));
    }

}
