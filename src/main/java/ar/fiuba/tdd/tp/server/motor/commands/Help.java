package ar.fiuba.tdd.tp.server.motor.commands;

import ar.fiuba.tdd.tp.server.exceptions.BadGameNameException;
import ar.fiuba.tdd.tp.server.motor.factories.FactoryGames;
import ar.fiuba.tdd.tp.server.motor.factories.GameEnum;

/**
 * Created by jorlando on 28/04/16.
 */
public class Help extends GameCommand {

    private String command;

    public Help() {
        super("help");
    }

    public String execute() {
        String[] gameName = this.command.split(" ");
        try {
            FactoryGames factory = GameEnum.getGame(gameName[1]);
            return factory.getHelp();
        } catch (BadGameNameException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean canProcessRequest(String request) {
        this.command = request;
        String[] gameName = this.command.split(" ");
        return (gameName[0].equals(this.getIdentifier()) && GameEnum.contains(gameName[1]));
    }
}
