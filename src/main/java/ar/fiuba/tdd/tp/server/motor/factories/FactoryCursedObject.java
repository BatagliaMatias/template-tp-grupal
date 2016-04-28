package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.games.CursedObject;
import ar.fiuba.tdd.tp.server.motor.games.Game;

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
