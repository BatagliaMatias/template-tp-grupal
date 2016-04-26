package ar.fiuba.tdd.tp;

public class TddGame extends Game {

    private static final int BEGIN = 0;
    private static final int SEND = 1;
    private static final int END = 2;

    private int state = BEGIN;

    @Override
    void initializeGame() {
        state = BEGIN;
    }

    @Override
    String makePlay(String input) {
        String output = null;

        if (state == BEGIN) {
            output = "The name of this Subject is ...";
            state = SEND;
        } else if (state == SEND) {
            if (input.equalsIgnoreCase("TDD")) {
                output = "YES. YOU WIN. THE GAME START AGAIN...";
                state = END;
            } else {
                output = "NO. TRY AGAIN";
            }
        }
        return output;
    }

    @Override
    boolean endOfGame() {
        return (state == END);
    }

    @Override
    void endGame() {
        // ...
    }
}