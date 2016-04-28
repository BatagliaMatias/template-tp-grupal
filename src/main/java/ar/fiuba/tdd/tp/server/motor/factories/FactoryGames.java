package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.games.Game;

public abstract class FactoryGames {

    public abstract Game create();

    public abstract String getHelp();

}
