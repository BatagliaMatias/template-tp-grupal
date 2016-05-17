package ar.fiuba.tdd.tp.engine.motor.factories;

import ar.fiuba.tdd.tp.engine.motor.games.Game;
import ar.fiuba.tdd.tp.engine.motor.games.TowerOfHanoi;

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
