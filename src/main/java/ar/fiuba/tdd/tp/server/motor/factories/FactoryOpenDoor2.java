package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.motor.games.Game;
import ar.fiuba.tdd.tp.server.motor.games.OpenDoor2;

public class FactoryOpenDoor2 extends FactoryGames {

    @Override
    public Game create() {
        return new OpenDoor2();
    }

    @Override
    public String getHelp() {
        return OpenDoor2.getHelp();
    }
}
