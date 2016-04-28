package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.Close;
import ar.fiuba.tdd.tp.server.motor.commands.LookAround;
import ar.fiuba.tdd.tp.server.motor.commands.Open;
import ar.fiuba.tdd.tp.server.motor.commands.Pick;
import ar.fiuba.tdd.tp.server.motor.entities.Box;
import ar.fiuba.tdd.tp.server.motor.entities.Key;
import ar.fiuba.tdd.tp.server.motor.entities.LockedDoor;
import ar.fiuba.tdd.tp.shared.Message;

public class OpenDoor2 extends Game {

    private static String helpMessage = "A door locked.. where can you find a key?";
    private Stage finalRoom;
    private Player player;

    public OpenDoor2() {

        Stage room1 = new Stage("Room 1");
        finalRoom = new Stage("Room 2");

        player = new Player();
        player.setlocation(room1);

        LockedDoor puerta = new LockedDoor("door", player, finalRoom);
        Key llave = new Key(puerta, room1, player.getInventory());

        Box box = new Box("box",room1);
        box.add(llave);

        room1.addEntity(puerta);
        room1.addEntity(box);

        commands.add(new LookAround(player));
        commands.add(new Open(puerta));
        commands.add(new Open(box));
        commands.add(new Close(box));
        commands.add(new Pick(llave, room1));

        includeWhatCanIdoWithCommand();
    }

    public static String getHelp() {
        return helpMessage;
    }

    @Override
    boolean isGameOver() {
        return player.getLocation() == finalRoom;
    }

    @Override
    String getGameOverMessage() {
        return Message.WIN.getText();
    }
}
