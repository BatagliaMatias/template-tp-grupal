package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.Close;
import ar.fiuba.tdd.tp.server.motor.commands.LookAround;
import ar.fiuba.tdd.tp.server.motor.commands.Open;
import ar.fiuba.tdd.tp.server.motor.commands.Pick;
import ar.fiuba.tdd.tp.server.motor.entities.*;

public class TreasureHunt extends Game {

    private static String helpMessage = "There is no help here, you are on your own";
    private Door puerta1;
    private LockedDoor puerta2;
    private LockedDoor puerta3;
    private Door puerta4;
    private PoisonousBox pbox;
    private Antidote antidoto;
    private Key key4;
    private Key key3;
    private Player player = new Player();
    private Stage initialRoom = new Stage("Room 1");
    private Stage room2 = new Stage("Room 2");
    private Stage room3 = new Stage("Room 3");
    private Stage room4 = new Stage("Room 4");
    private Stage room5 = new Stage("Room 5");
    private PickableGameEntity treasure = new Treasure(room2, player.getInventory());
    private Box wardrobe;
    private Box box;

    public TreasureHunt() {
        initDoors();
        initItems();

        initialRoom.addEntity(wardrobe);
        initialRoom.addEntity(puerta1);
        initialRoom.addEntity(puerta2);

        room2.addEntity(pbox);
        room2.addEntity(puerta3);

        room3.addEntity(antidoto);
        room4.addEntity(puerta4);
        room5.addEntity(key3);
        player.setlocation(initialRoom);

        initCommands();
    }

    public static String getHelp() {
        return helpMessage;
    }

    private void initDoors() {
        puerta1 = new Door("door1", player, room2);
        puerta2 = new LockedDoor("door2", player, room4);
        puerta3 = new LockedDoor("door3", player, room3);
        puerta4 = new Door("door4", player, room5);
    }

    private void initItems() {
        wardrobe = new Box("wardrobe", initialRoom);
        box = new Box("box", initialRoom);
        wardrobe.add(box);
        key4 = new Key(puerta2, initialRoom, this.player.getInventory());
        box.add(key4);
        antidoto = new Antidote(player, room3, this.player.getInventory());
        key3 = new Key("key2", puerta3, room2, this.player.getInventory());
        pbox = new PoisonousBox("oddLookingBox", room2, player);
        pbox.add(treasure);
    }

    private void initCommands() {
        commands.add(new Pick(key4, initialRoom));
        commands.add(new Open(wardrobe));
        commands.add(new LookAround(player));
        commands.add(new Open(box));
        commands.add(new Pick(antidoto, room3));
        commands.add(new Open(puerta1));
        commands.add(new Open(puerta2));
        commands.add(new Open(puerta4));
        commands.add(new Open(puerta3));
        commands.add(new Open(pbox));
        commands.add(new Pick(treasure, room2));
        commands.add(new Close(pbox));
        commands.add(new Pick(key3, room5));
        commands.add(new Close(wardrobe));
        commands.add(new Close(box));
        includeWhatCanIdoWithCommand();
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