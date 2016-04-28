package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.games.Game;
import ar.fiuba.tdd.tp.server.motor.games.PickStick;

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
