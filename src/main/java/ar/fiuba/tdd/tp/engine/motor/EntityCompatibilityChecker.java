package ar.fiuba.tdd.tp.engine.motor;

import ar.fiuba.tdd.tp.engine.motor.entities.GameEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityCompatibilityChecker {

    protected List<EntityPair> incompatiblePairs = new ArrayList<EntityPair>();

    public void addIncompatiblePair(GameEntity entityA, GameEntity entityB) {
        incompatiblePairs.add(new EntityPair(entityA, entityB));
    }

    public boolean isCompatible(GameEntity entityA, GameEntity entityB) {
        for (EntityPair pair : incompatiblePairs) {
            if (pair.equals(entityA, entityB)) {
                return false;
            }
        }
        return true;
    }

    public boolean isCompatible(EntityContainer container) {
        Iterator<GameEntity> outerIter = container.getIter();
        while (outerIter.hasNext()) {
            GameEntity entityA = outerIter.next();
            Iterator<GameEntity> inerIter = container.getIter();
            while (inerIter.hasNext()) {
                GameEntity entityB = inerIter.next();
                if (!isCompatible(entityA, entityB)) {
                    return false;
                }
            }

        }
        return true;
    }
}
