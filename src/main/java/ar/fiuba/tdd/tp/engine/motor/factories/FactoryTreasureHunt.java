package ar.fiuba.tdd.tp.engine.motor.factories;

import ar.fiuba.tdd.tp.engine.motor.games.Game;
import ar.fiuba.tdd.tp.engine.motor.games.TreasureHunt;

public class FactoryTreasureHunt extends FactoryGames {

    @Override
    public Game create() {
        return new TreasureHunt();
    }

    @Override
    public String getHelp() {
        return TreasureHunt.getHelp();
    }
}