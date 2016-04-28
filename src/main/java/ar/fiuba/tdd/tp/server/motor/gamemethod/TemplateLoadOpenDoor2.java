package ar.fiuba.tdd.tp.server.motor.gamemethod;

import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.Close;
import ar.fiuba.tdd.tp.server.motor.commands.Open;
import ar.fiuba.tdd.tp.server.motor.entities.Box;
import ar.fiuba.tdd.tp.server.motor.entities.Key;
import ar.fiuba.tdd.tp.server.motor.games.OpenDoor;

public class TemplateLoadOpenDoor2 extends TemplateLoadObjects{

    private Box box;

    @Override
    protected void loadRoom(Stage room,Key key) {

        this.box = new Box(room);
        box.add(key);

        room.addEntity(box);

    }

    @Override
    protected void loadCommands(OpenDoor gameOpenDoor) {

        gameOpenDoor.setCommand(new Open(box));
        gameOpenDoor.setCommand(new Close(box));

    }

}
