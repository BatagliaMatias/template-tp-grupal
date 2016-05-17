package ar.fiuba.tdd.tp.engine.motor.factories;


import ar.fiuba.tdd.tp.engine.motor.games.Game;
import ar.fiuba.tdd.tp.engine.motor.games.OpenDoor;

public class FactoryOpenDoor extends FactoryGames {

    @Override
    public Game create() {
        return new OpenDoor();
    }

    @Override
    public String getHelp() {
        return OpenDoor.getHelp();
    }

}
