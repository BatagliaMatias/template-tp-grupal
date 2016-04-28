package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.games.AbrirPuerta2;
import ar.fiuba.tdd.tp.server.motor.games.Game;

public class FactoryOpenDoor2 extends FactoryGames {

    @Override
    public Game create() {
        return new AbrirPuerta2();
    }

    @Override
    public String getHelp() {
        return AbrirPuerta2.getHelp();
    }
}
