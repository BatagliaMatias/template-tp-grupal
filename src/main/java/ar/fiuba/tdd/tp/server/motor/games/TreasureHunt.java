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

        // OJO: a las puertas hay que agregarlas al stage donde estan y al que llevan (estan en 2 stages al mismo tiempo)

        Door puerta1 = new Door("openDoor", player, room2);
        LockedDoor puerta2 = new LockedDoor("closedDoor", player, room3);

        treasure = new Treasure();

        player.setlocation(initialRoom);
        room2.addEntity(treasure);
        initialRoom.addEntity(new Col());
        initialRoom.addEntity(puerta1);
        room2.addEntity(puerta1);
        initialRoom.addEntity(puerta2);
        room3.addEntity(puerta2);

        commands.add(new LookAround(player));
        commands.add(new Open(puerta1));
        commands.add(new Open(puerta2));

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