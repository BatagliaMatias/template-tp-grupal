package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.games.Game;
import ar.fiuba.tdd.tp.server.motor.games.WolfSheepCol;

public class FactoryWolfSheepCol extends FactoryGames {

    @Override
    public Game create() {
        return new WolfSheepCol();
    }

    @Override
    public String getHelp() {
        return WolfSheepCol.getHelp();
    }
}