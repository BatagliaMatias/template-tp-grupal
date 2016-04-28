package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.LookAround;
import ar.fiuba.tdd.tp.server.motor.commands.Open;
import ar.fiuba.tdd.tp.server.motor.commands.Pick;
import ar.fiuba.tdd.tp.server.motor.entities.*;

public class TreasureHunt extends Game {

    private static String helpMessage = "There is no help here, you are on your own";
    Stage room3 = new Stage("Room 3");
    private Player player = new Player();
    private PickableGameEntity treasure = new Treasure(room3, player.getInventory());
    private Stage initialRoom = new Stage("Room 1");

    public TreasureHunt() {

        // initialRoom contiene 2 puertas, una cerrada y otra abierta, llevan a room 2 y room 4 respectivamente
        Stage room2 = new Stage("Room 2");
        Stage room4 = new Stage("Room 4");
        Door puerta1 = new Door("door1", player, room2);
        LockedDoor puerta2 = new LockedDoor("door2", player, room4);
        initialRoom.addEntity(puerta1);
        initialRoom.addEntity(puerta2);
        PoisonousBox pbox1 = new PoisonousBox("oddLookingBox", initialRoom, player);
        pbox1.add(new Col());
        initialRoom.addEntity(pbox1);

        Stage room5 = new Stage("Room 5");
        // room 2 contiene una puerta cerrada a room 3
        LockedDoor puerta3 = new LockedDoor("door3", player, room3);
        room2.addEntity(puerta3);

        // room 4 contiene una puerta abierta a room 5
        LockedDoor puerta4 = new LockedDoor("door4", player, room5);
        room4.addEntity(puerta4);


        player.setlocation(initialRoom);
        room3.addEntity(treasure);
/*
        commands.add(new LookAround(player));
        commands.add(new Open(puerta1));
        commands.add(new Open(puerta2));
        commands.add(new Open(puerta3));
        commands.add(new Open(puerta4));
        commands.add(new Open(pbox1));
        commands.add(new Pick(treasure, room3));

        includeWhatCanIdoWithCommand();*/
    }

    public static String getHelp() {
        return helpMessage;
    }

    @Override
    boolean isGameOver() {
        return ((player.getLocation() == initialRoom) && (player.getInventory().containsEntity(treasure))) || player.isDead();
    }

    @Override
    String getGameOverMessage() {
        if (player.isDead()) {
            return "You died, you crossed a door while being poisoned";
        }
        return "Congratulations, you won";
    }
}