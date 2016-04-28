package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.LookAround;
import ar.fiuba.tdd.tp.server.motor.commands.Pick;
import ar.fiuba.tdd.tp.server.motor.entities.Stick;

public class PickStick extends Game{

    private Stick stick;
    private Player player;
    private static String helpMessage = "You have to pick that stick over there.";

    public PickStick() {

        Stage room = new Stage("Room 1");

        this.player = new Player();
        this.player.setlocation(room);

        this.stick = new Stick(room,this.player.getInventory());

        room.addEntity(this.stick);

        this.commands.add(new LookAround(this.player));
        this.commands.add(new Pick(this.stick, room));

    }

    @Override
    public boolean isGameOver() {
        return this.player.getInventory().containsEntity(this.stick);
    }

    @Override
    public String getGameOverMessage() {
        return "You won the game!";
    }

    public static String getHelp() {
        return helpMessage;
    }
}
