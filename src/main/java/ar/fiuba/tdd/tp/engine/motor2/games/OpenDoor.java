package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.engine.motor2.Container;
import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameBuilder;
import ar.fiuba.tdd.tp.engine.motor2.PlayerCommand;

public class OpenDoor implements GameBuilder {
    @SuppressWarnings("CPD-START")

    @Override
    public Game build() {
        Game openDoorGame = new Game();
        Container door = new Container("door");
        Container roomA = new Container("roomA");
        roomA.setComponent(door);
        openDoorGame.setInitialPosition(roomA);
        Container roomB = new Container("roomB");

        PlayerCommand openDoor = new PlayerCommand("open door");
        openDoor.setPlayerCommand((Container player) -> {
            roomB.setComponent(player);
            return "moved to room B";
        });

        openDoorGame.setPlayerCommand(openDoor);
        openDoorGame.setWinCondition((Container player) -> (roomB.contains(player)));
        return openDoorGame;
    }


}
