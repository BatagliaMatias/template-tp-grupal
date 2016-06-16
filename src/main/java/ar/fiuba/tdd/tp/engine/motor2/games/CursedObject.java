package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.engine.motor2.Container;
import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameBuilder;
import ar.fiuba.tdd.tp.engine.motor2.PlayerCommand;

public class CursedObject implements GameBuilder {

    @SuppressWarnings("CPD-START")

    @Override
    public Game build() {

        Container doorAB = new Container("doorAB");
        Container doorBC = new Container("doorBC");
        Container roomB = new Container("roomB");
        Container thief = new Container("thief");
        roomB.setComponent(thief);
        roomB.setComponent(doorAB);
        roomB.setComponent(doorBC);
        Container roomC = new Container("roomC");
        roomC.setComponent(doorBC);
        Container cursedObject = new Container("cursedObject");
        Container roomA = new Container("roomA");
        roomA.setComponent(cursedObject);
        roomA.setComponent(doorAB);
        Game cursedObjectGame = new Game();
        cursedObjectGame.setInitialPosition(roomA);

        PlayerCommand openDoorAB = new PlayerCommand("open door AB");
        openDoorAB.setPlayerCommand((Container player) -> {
                if (player.getParent() == roomA) {
                    if (player.contains(cursedObject)) {
                        roomB.setComponent(player);
                        return "moved to room B";
                    } else {
                        return "you can't open the door";
                    }
                } else {
                    return "you are crazy";
                }
            });

        PlayerCommand openDoorBC = new PlayerCommand("open door BC");
        openDoorBC.setPlayerCommand((Container player) -> {
                if (player.getParent() == roomB) {
                    if (!player.contains(cursedObject)) {
                        roomC.setComponent(player);
                        return "moved to room C";
                    }
                    return "you can't open the door";
                }
                return "you are crazy";
            });

        PlayerCommand takeCursedObject = new PlayerCommand("take cursedObject");
        takeCursedObject.setPlayerCommand((Container player) -> {
                if (player.getParent() == roomA) {
                    if (roomA.contains(cursedObject)) {
                        player.setComponent(cursedObject);
                        roomA.removeComponent(cursedObject);
                        return "CursedObject has been taken";
                    }
                    return "There is no cursedObject";
                }
                return "you are crazy";
            });

        PlayerCommand talkThief = new PlayerCommand("talk thief");
        talkThief.setPlayerCommand((Container player) -> {
                if (player.getParent() == roomB) {
                    if (player.contains(cursedObject)) {
                        thief.setComponent(cursedObject);
                        player.removeComponent(cursedObject);
                        return "Thief: give me that nice object amigo!";
                    }
                    return "Dont talk to me";
                }
                return "you are crazy";
            });


        cursedObjectGame.setPlayerCommand(openDoorAB);
        cursedObjectGame.setPlayerCommand(openDoorBC);
        cursedObjectGame.setPlayerCommand(takeCursedObject);
        cursedObjectGame.setPlayerCommand(talkThief);
        cursedObjectGame.setWinCondition((Container player) -> (roomC.contains(player)));
        return cursedObjectGame;
    }
}
