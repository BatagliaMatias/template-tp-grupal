package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.*;
import ar.fiuba.tdd.tp.server.motor.entities.Door;
import ar.fiuba.tdd.tp.server.motor.entities.Key;
import ar.fiuba.tdd.tp.shared.Message;

public class OpenDoor extends Game {

    private static String helpMessage = "A door locked.. where can you find a key?";
    protected Stage finalRoom;
    protected Stage room1;
    protected Player player;
    protected Key key;

    public OpenDoor() {

        this.room1 = new Stage("Room 1");
        this.finalRoom = new Stage("Room 2");

        this.player = new Player();
        this.player.setlocation(this.room1);

        Door puerta = new Door("door",this.player, this.finalRoom);
        this.key = new Key(puerta, this.room1, this.player.getInventory());

        this.room1.addEntity(puerta);
        this.room1.addEntity(this.key);

        this.commands.add(new LookAround(this.player));
        this.commands.add(new Open(puerta));
        this.commands.add(new Pick(this.key, this.room1));

    }

    @Override
    boolean isGameOver() {
        return player.getLocation() == this.finalRoom;
    }

    @Override
    String getGameOverMessage() {
        return Message.WIN.getText();
        //return "GANASTE!. A esto le falta variar el mensaje si perdes";
    }

    public static String getHelp() {
        return helpMessage;
    }
}
