package ar.fiuba.tdd.tp.server.motor.commands;


import ar.fiuba.tdd.tp.shared.Message;

/**
 * Created by jorlando on 28/04/16.
 */
public class Exit extends GameCommand {

    public Exit() {
        super(Message.EXIT_MESSAGE.getText(), "");
    }

    public String execute() {
        return Message.BYE.getText();
    }

}
