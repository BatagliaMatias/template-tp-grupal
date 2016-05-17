package ar.fiuba.tdd.tp.engine.motor.factories;

import ar.fiuba.tdd.tp.engine.motor.games.Game;
import ar.fiuba.tdd.tp.engine.motor.games.PickStick;

public class FactoryPickStick extends FactoryGames {

    @Override
    public Game create() {
        return new PickStick();
    }

    @Override
    public String getHelp() {
        return PickStick.getHelp();
    }

}
