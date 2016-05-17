package ar.fiuba.tdd.tp.engine.motor.factories;

import ar.fiuba.tdd.tp.engine.motor.games.Game;

public abstract class FactoryGames {

    public abstract Game create();

    public abstract String getHelp();

}
