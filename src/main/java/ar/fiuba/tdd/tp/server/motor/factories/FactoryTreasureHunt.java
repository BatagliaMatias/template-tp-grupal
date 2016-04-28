package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.games.Game;
import ar.fiuba.tdd.tp.server.motor.games.TreasureHunt;
import ar.fiuba.tdd.tp.server.motor.games.WolfSheepCol;

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