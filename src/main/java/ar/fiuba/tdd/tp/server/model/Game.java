package ar.fiuba.tdd.tp.server.model;


public abstract class Game {
    abstract void initializeGame();

    abstract String makePlay(String input);

    abstract boolean endOfGame();

    abstract void endGame();

    /* template method : */
    public String processInput(String theInput) {
        String output = null;

        output = makePlay(theInput);
        if (endOfGame()) {
            endGame();
            //... (por ej llamar a callback que finalize la coneccion)
        }

        return output;
    }
}

