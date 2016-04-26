package ar.fiuba.tdd.tp;

public class Motor {

    private Game game;

    public Motor() {
        this.game = new AbrirPuerta2();
    }

    public String processInput(String input) {
        return this.game.processInput(input);
    }

}
