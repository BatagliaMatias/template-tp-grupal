package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.*;
import ar.fiuba.tdd.tp.server.motor.entities.Door;
import ar.fiuba.tdd.tp.server.motor.entities.Key;
import ar.fiuba.tdd.tp.server.motor.entities.Thief;

public class CursedObject extends Game{

    private static String helpMessage = "A door locked, a cursed key.. where can you escape?";
    private Player player;
    private Stage destinationRoom;
    private Thief thief;

    public CursedObject() {

        Stage originRoom = new Stage("Room1");
        Stage secondRoom = new Stage("Room2");
        this.destinationRoom = new Stage("Room3");

        this.player = new Player();


        Door door1 = new Door("door1",this.player, secondRoom);
        Door door2 = new Door("door2",this.player, this.destinationRoom);

        Key key = new Key(door1, originRoom, player.getInventory());
        player.setlocation(originRoom);
        this.thief = new Thief(door2,key,this.player.getInventory());

        originRoom.addEntity(key);
        originRoom.addEntity(door1);

        secondRoom.addEntity(door2);
        secondRoom.addEntity(thief);

        commands.add(new Talk("Hello","Hi!",thief));
        commands.add(new Talk("Bye","Bye!",thief));
        commands.add(new LookAround(player));
        commands.add(new Open(door1));
        commands.add(new Open(door2));
        commands.add(new Pick(key, originRoom));

    }

    @Override
    public boolean isGameOver() {
        return this.player.getLocation() == this.destinationRoom;
    }

    @Override
    public String getGameOverMessage() {
        return "Congratulations!! You won the game!";
    }
}
