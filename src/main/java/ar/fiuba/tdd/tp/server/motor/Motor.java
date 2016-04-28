package ar.fiuba.tdd.tp.server.motor;

import ar.fiuba.tdd.tp.server.exceptions.BadGameNameException;
import ar.fiuba.tdd.tp.server.motor.factories.FactoryGames;
import ar.fiuba.tdd.tp.server.motor.factories.GameEnum;
import ar.fiuba.tdd.tp.server.motor.games.Game;

public class Motor {

    private Game game;

    public Motor(String gameName) throws BadGameNameException {
        FactoryGames factory = GameEnum.getGame(gameName);
        this.game = factory.create();
    }

    public String getWelcomeMessage() {
        return this.game.getWelcomeMessage();
    }

    public String processInput(String input) {
        return this.game.processInput(input);
    }

}
