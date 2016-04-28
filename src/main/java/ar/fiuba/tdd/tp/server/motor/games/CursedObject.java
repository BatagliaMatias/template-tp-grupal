package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.LookAround;
import ar.fiuba.tdd.tp.server.motor.commands.Open;
import ar.fiuba.tdd.tp.server.motor.commands.Pick;
import ar.fiuba.tdd.tp.server.motor.commands.Talk;
import ar.fiuba.tdd.tp.server.motor.commands.proxycommands.ProxyCommand;
import ar.fiuba.tdd.tp.server.motor.entities.Key;
import ar.fiuba.tdd.tp.server.motor.entities.LockedDoor;
import ar.fiuba.tdd.tp.server.motor.entities.Thief;

public class CursedObject extends Game {

    private static String helpMessage = "A door locked, a cursed key.. where can you escape?";
    private Player player = new Player();
    private Stage destinationRoom = new Stage("Room3");

    public CursedObject() {

        Stage originRoom = new Stage("Room1");
        Stage secondRoom = new Stage("Room2");
        LockedDoor door1 = new LockedDoor("door1", this.player, secondRoom);
        Key key = new Key(door1, originRoom, player.getInventory());
        player.setlocation(originRoom);

        originRoom.addEntity(key);
        originRoom.addEntity(door1);

        LockedDoor door2 = new LockedDoor("door2", this.player, this.destinationRoom);
        secondRoom.addEntity(door2);
        Thief thief = new Thief(door2, key, this.player.getInventory());
        secondRoom.addEntity(thief);

        commands.add(new ProxyCommand(new Talk("Hello", "Hi!", thief), this.player, secondRoom));
        commands.add(new ProxyCommand(new Talk("Bye", "Bye!", thief), this.player, secondRoom));

        commands.add(new LookAround(player));
        commands.add(new Open(door1));
        commands.add(new ProxyCommand(new Open(door2), this.player, secondRoom));
        commands.add(new Pick(key, originRoom));
        includeWhatCanIdoWithCommand();
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
