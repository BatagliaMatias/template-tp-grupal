package ar.fiuba.tdd.tp.engine.motor;

import ar.fiuba.tdd.tp.engine.motor.factories.FactoryGames;
import ar.fiuba.tdd.tp.engine.motor.factories.GameEnum;
import ar.fiuba.tdd.tp.engine.motor.games.Game;
import ar.fiuba.tdd.tp.server.exceptions.BadGameNameException;

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
