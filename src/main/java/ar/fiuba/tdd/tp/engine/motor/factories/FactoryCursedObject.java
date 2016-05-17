package ar.fiuba.tdd.tp.engine.motor.factories;

import ar.fiuba.tdd.tp.engine.motor.games.CursedObject;
import ar.fiuba.tdd.tp.engine.motor.games.Game;

public class FactoryCursedObject extends FactoryGames {
    @Override
    public Game create() {
        return new CursedObject();
    }

    @Override
    public String getHelp() {
        return CursedObject.getHelp();
    }

}
