package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.games.Game;
import ar.fiuba.tdd.tp.server.motor.games.TowerOfHanoi;

public class FactoryTowerOfHanoi extends FactoryGames {

    @Override
    public Game create() {
        return new TowerOfHanoi();
    }

    @Override
    public String getHelp() {
        return TowerOfHanoi.getHelp();
    }
}
