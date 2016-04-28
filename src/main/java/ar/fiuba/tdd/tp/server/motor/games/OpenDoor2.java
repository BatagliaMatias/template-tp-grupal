package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.commands.Close;
import ar.fiuba.tdd.tp.server.motor.commands.Open;
import ar.fiuba.tdd.tp.server.motor.entities.Box;

public class OpenDoor2 extends OpenDoor {

    public OpenDoor2() {

        super();

        Box box = new Box("box", this.room1);

        box.add(key);

        this.room1.addEntity(box);
        this.room1.removeEntity(key);

        this.commands.add(new Open(box));
        this.commands.add(new Close(box));

        includeWhatCanIdoWithCommand();

    }

}