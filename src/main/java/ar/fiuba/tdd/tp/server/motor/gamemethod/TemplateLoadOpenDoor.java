package ar.fiuba.tdd.tp.server.motor.gamemethod;

import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.entities.Key;
import ar.fiuba.tdd.tp.server.motor.games.OpenDoor;

public class TemplateLoadOpenDoor extends TemplateLoadObjects {

    @Override
    protected void loadRoom(Stage room, Key key) {

        room.addEntity(key);

    }

    @Override
    protected void loadCommands(OpenDoor gameOpenDoor) {
        //No utiliza el metodo
    }

}
