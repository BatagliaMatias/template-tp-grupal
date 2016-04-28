package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.LookAround;
import ar.fiuba.tdd.tp.server.motor.commands.Open;
import ar.fiuba.tdd.tp.server.motor.entities.*;
import ar.fiuba.tdd.tp.shared.Message;

public class TreasureHunt extends Game {

    private static String helpMessage = "There is no help here, you are on your own";
    private Stage initialRoom;
    private Player player;
    private GameEntity treasure;

    public TreasureHunt() {


        Stage initialRoom = new Stage("Room 1");
        Stage room2 = new Stage("Room 2");
        Stage room3 = new Stage("Room 3");
        Stage room4 = new Stage("Room 4");
        Stage room5 = new Stage("Room 5");

        player = new Player();


        // initialRoom contiene 2 puertas, una cerrada y otra abierta, llevan a room 2 y room 4 respectivamente
        Door puerta1 = new Door("door1", player, room2);
        LockedDoor puerta2 = new LockedDoor("door2", player, room4);
        initialRoom.addEntity(puerta1);
        initialRoom.addEntity(puerta2);

        // room 2 contiene una puerta cerrada a room 3
        LockedDoor puerta3 = new LockedDoor("door3",player,room3);
        room2.addEntity(puerta3);

        // room 4 contiene una puerta abierta a room 5
        LockedDoor puerta4 = new LockedDoor("door4",player,room5);
        room4.addEntity(puerta3);


        treasure = new Treasure();

        player.setlocation(initialRoom);
        room2.addEntity(treasure);
        initialRoom.addEntity(new Col());

        commands.add(new LookAround(player));
        commands.add(new Open(puerta1));
        commands.add(new Open(puerta2));
        commands.add(new Open(puerta3));
        commands.add(new Open(puerta4));

        includeWhatCanIdoWithCommand();
    }

    public static String getHelp() {
        return helpMessage;
    }

    @Override
    boolean isGameOver() {
        return (player.getLocation() == initialRoom) && (player.getInventory().containsEntity(treasure));
    }

    @Override
    String getGameOverMessage() {
        return Message.WIN.getText();
    }
}