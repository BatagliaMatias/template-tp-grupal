package ar.fiuba.tdd.tp.server.motor.gamemethod;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.Close;
import ar.fiuba.tdd.tp.server.motor.commands.LookAround;
import ar.fiuba.tdd.tp.server.motor.commands.Open;
import ar.fiuba.tdd.tp.server.motor.commands.Pick;
import ar.fiuba.tdd.tp.server.motor.entities.Box;
import ar.fiuba.tdd.tp.server.motor.entities.Door;
import ar.fiuba.tdd.tp.server.motor.entities.Key;
import ar.fiuba.tdd.tp.server.motor.games.OpenDoor;

public abstract class TemplateLoadObjects {

    public void load(OpenDoor gameOpenDoor) {

        Stage room1 = new Stage("Room 1");
        Stage finalRoom = new Stage("Room 2");

        Player player = new Player();
        player.setlocation(room1);

        Door puerta = new Door("door",player, finalRoom);
        Key key = new Key(puerta, room1, player.getInventory());

        this.loadRoom(room1,key);

        room1.addEntity(puerta);

        gameOpenDoor.setCommand(new LookAround(player));
        gameOpenDoor.setCommand(new Open(puerta));
        gameOpenDoor.setCommand(new Pick(key, room1));

        this.loadCommands(gameOpenDoor);

        gameOpenDoor.setPlayer(player);
        gameOpenDoor.setFinalRoom(finalRoom);

    }

    protected abstract void loadRoom(Stage room,Key key);

    protected abstract void loadCommands(OpenDoor gameOpenDoor);

}
