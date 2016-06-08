package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.engine.motor2.Container;
import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameBuilder;
import ar.fiuba.tdd.tp.engine.motor2.PlayerCommand;

public class CursedObject implements GameBuilder {
    @SuppressWarnings("CPD-START")

    @Override
    public Game build() {
        Game cursedObjectGame = new Game();
        Container doorAB = new Container("doorAB");
        Container doorBC = new Container("doorBC");
        Container roomA = new Container("roomA");
        Container roomB = new Container("roomB");
        Container roomC = new Container("roomC");
        Container cursedObject = new Container("cursedObject");
        Container thief = new Container("thief");
        roomA.setComponent(cursedObject);
        roomB.setComponent(thief);
        roomA.setComponent(doorAB);
        roomB.setComponent(doorAB);
        roomB.setComponent(doorBC);
        roomC.setComponent(doorBC);
        cursedObjectGame.setInitialPosition(roomA);

        PlayerCommand openDoorAB = new PlayerCommand("open door AB");
        openDoorAB.setPlayerCommand((Container player) -> {
            if (player.getParent() == roomA) {
                if(player.contains(cursedObject)){
                    roomB.setComponent(player);
                    return "moved to room B";
                }
                else{
                    return "you can't open the door";
                }
            } else {
                return "you are crazy";
            }
        });

        PlayerCommand openDoorBC = new PlayerCommand("open door BC");
        openDoorBC.setPlayerCommand((Container player) -> {
            if (player.getParent() == roomB) {
                if(!player.contains(cursedObject)){
                    roomC.setComponent(player);
                    return "moved to room C";
                }
                else{
                    return "you can't open the door";
                }
            } else {
                return "you are crazy";
            }
        });

        PlayerCommand takeCursedObject = new PlayerCommand("take cursedObject");
        takeCursedObject.setPlayerCommand((Container player) -> {
            if (player.getParent() == roomA) {
                if(roomA.contains(cursedObject)){
                    player.setComponent(cursedObject);
                    roomA.removeComponent(cursedObject);
                    return "CursedObject has been taken";
                }
                else{
                    return "There is no cursedObject";
                }
            } else {
                return "you are crazy";
            }
        });

        PlayerCommand talkThief = new PlayerCommand("talk thief");
        talkThief.setPlayerCommand((Container player) -> {
            if (player.getParent() == roomB) {
                if(player.contains(cursedObject)){
                    thief.setComponent(cursedObject);
                    player.removeComponent(cursedObject);
                    return "Thief: give me that nice object amigo!";
                }
                else{
                    return "Dont talk to me";
                }
            } else {
                return "you are crazy";
            }
        });


        cursedObjectGame.setPlayerCommand(openDoorAB);
        cursedObjectGame.setPlayerCommand(openDoorBC);
        cursedObjectGame.setPlayerCommand(takeCursedObject);
        cursedObjectGame.setPlayerCommand(talkThief);
        cursedObjectGame.setWinCondition((Container player) -> (roomC.contains(player)));
        return cursedObjectGame;
    }
}
