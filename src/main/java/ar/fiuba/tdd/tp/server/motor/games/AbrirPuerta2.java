package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.Close;
import ar.fiuba.tdd.tp.server.motor.commands.LookAround;
import ar.fiuba.tdd.tp.server.motor.commands.Open;
import ar.fiuba.tdd.tp.server.motor.commands.Pick;
import ar.fiuba.tdd.tp.server.motor.entities.Box;
import ar.fiuba.tdd.tp.server.motor.entities.Door;
import ar.fiuba.tdd.tp.server.motor.entities.Key;

public class AbrirPuerta2 extends Game {

    private static String helpMessage = "A door locked.. where can you find a key?";
    private Stage finalRoom;
    private Player player;

    public AbrirPuerta2() {

        Stage room1 = new Stage("Room 1");
        finalRoom = new Stage("Room 2");

        player = new Player();
        player.setlocation(room1);

        Door puerta = new Door(player, finalRoom);
        Key llave = new Key(puerta, room1, player.getInventory());

        Box box = new Box(room1);
        box.add(llave);

        room1.addEntity(puerta);
        room1.addEntity(box);

        commands.add(new LookAround(player));
        commands.add(new Open(puerta));
        commands.add(new Open(box));
        commands.add(new Close(box));
        commands.add(new Pick(llave, room1));
    }

    @Override
    boolean isGameOver() {
        return player.getLocation() == finalRoom;
    }

    @Override
    String getGameOverMessage() {
        return "GANASTE!. A esto le falta variar el mensaje si perdes";
    }

    public static String getHelp() {
        return helpMessage;
    }
}
