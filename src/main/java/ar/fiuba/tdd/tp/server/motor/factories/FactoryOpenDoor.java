package ar.fiuba.tdd.tp.server.motor.factories;


import ar.fiuba.tdd.tp.server.motor.games.Game;
import ar.fiuba.tdd.tp.server.motor.games.OpenDoor;

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
