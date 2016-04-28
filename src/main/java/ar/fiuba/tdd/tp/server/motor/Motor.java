package ar.fiuba.tdd.tp.server.motor;

import ar.fiuba.tdd.tp.server.motor.games.AbrirPuerta2;
import ar.fiuba.tdd.tp.server.motor.games.Game;

public class Motor {

    private Game game;

    public Motor() {
        this.game = new AbrirPuerta2();
    }

    public String getWelcomeMessage() {
        return this.game.getWelcomeMessage();
    }

    public String processInput(String input) {
        return this.game.processInput(input);
    }

}
