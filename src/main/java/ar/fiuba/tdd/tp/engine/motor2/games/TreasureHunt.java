package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.engine.motor2.Container;
import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameBuilder;
import ar.fiuba.tdd.tp.engine.motor2.PlayerCommand;


public class TreasureHunt implements GameBuilder {
    @SuppressWarnings("CPD-START")

    @Override
    public Game build() {

        Game treasureHunt = new Game();

        Container room1 = new Container("room1");
        treasureHunt.setInitialPosition(room1);
        Container room2 = new Container("room2");
        Container room3 = new Container("room3");

        Container door1 = new Container("door1");
        room1.setComponent(door1);
        room2.setComponent(door1);
        Container door2 = new Container("door2");
        room1.setComponent(door2);
        room3.setComponent(door2);

        Container treasure = new Container("treasure");
        room3.setComponent(treasure);

        Container box = new Container("box");
        room2.setComponent(box);
        Container key = new Container("key");
        //box.setComponent(key);


        PlayerCommand openDoor1 = new PlayerCommand("open door1");
        openDoor1.setPlayerCommand((Container player) -> {
                if (player.getParent() == room1) {
                    room1.removeComponent(player);
                    room2.setComponent(player);
                    return "moved to room2";
                }
                if (player.getParent() == room2) {
                    room2.removeComponent(player);
                    room1.setComponent(player);
                    return "moved to room1";
                }
                return "tried to open door 1 but its not there!";
            });

        PlayerCommand openDoor2 = new PlayerCommand("open door2");
        openDoor2.setPlayerCommand((Container player) -> {
                if (!player.contains(key)) {
                    return "tried to open the door2 but its locked";
                }
                if (player.getParent() == room1) {
                    room1.removeComponent(player);
                    room3.setComponent(player);
                    return "moved to room2";
                }
                if (player.getParent() == room3) {
                    room3.removeComponent(player);
                    room1.setComponent(player);
                    return "moved to room1";
                }
                return "tried to open door 2 but its not there!";
            });

        PlayerCommand openBox = new PlayerCommand("open box");
        openBox.setPlayerCommand((Container player) -> {
                if (player.getParent() != box.getParent()) {
                    return "tried to open the box but its not ni that room";
                }
                player.getParent().setComponent(key);
                return "opened the box and there is a key there!";
            });

        PlayerCommand pickKey = new PlayerCommand("pick key");
        pickKey.setPlayerCommand((Container player) -> {
                if (player.getParent() != key.getParent()) {
                    return "tried to pick the key but there is no key there";
                }
                key.getParent().removeComponent(key);
                player.setComponent(key);
                return "picked up the key!";
            });

        PlayerCommand pickTreasure = new PlayerCommand("pick treasure");
        pickTreasure.setPlayerCommand((Container player) -> {
                if (player.getParent() != treasure.getParent()) {
                    return "tried to pick the treasure but there is no treasure there";
                }
                player.getParent().removeComponent(treasure);
                player.setComponent(treasure);
                return "picked up the treasure!!!!!";
            });

        treasureHunt.setPlayerCommand(openDoor1);
        treasureHunt.setPlayerCommand(openDoor2);
        treasureHunt.setPlayerCommand(openBox);
        treasureHunt.setPlayerCommand(pickKey);
        treasureHunt.setPlayerCommand(pickTreasure);

        treasureHunt.addTimedEvent(true, 50000, () -> {
                if (box.getParent() == room2) {
                    room2.removeComponent(box);
                    room1.setComponent(box);
                } else {
                    room1.removeComponent(box);
                    room2.setComponent(box);
                }
                return "something happened!";
            });

        treasureHunt.setWinCondition((Container player) ->
                (player.contains(treasure)));

        return treasureHunt;
        //@SuppressWarnings("CPD-END")
    }
}