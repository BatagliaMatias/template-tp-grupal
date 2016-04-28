package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.*;
import ar.fiuba.tdd.tp.server.motor.entities.Box;
import ar.fiuba.tdd.tp.server.motor.entities.Door;
import ar.fiuba.tdd.tp.server.motor.entities.Key;
import ar.fiuba.tdd.tp.server.motor.gamemethod.TemplateLoadObjects;
import ar.fiuba.tdd.tp.shared.Message;

public class OpenDoor extends Game {

    private static String helpMessage = "A door locked.. where can you find a key?";
    private Stage finalRoom;
    private Player player;

    public OpenDoor(TemplateLoadObjects template) {

        template.load(this);

    }

    @Override
    boolean isGameOver() {
        return player.getLocation() == finalRoom;
    }

    @Override
    String getGameOverMessage() {
        return Message.WIN.getText();
        //return "GANASTE!. A esto le falta variar el mensaje si perdes";
    }

    public void setCommand(GameCommand command) {

        this.commands.add(command);

    }

    public void setPlayer(Player player) {

        this.player = player;

    }

    public void setFinalRoom(Stage room) {

        this.finalRoom = room;

    }

    public static String getHelp() {
        return helpMessage;
    }
}
