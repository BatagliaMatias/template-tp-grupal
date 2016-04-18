package ar.fiuba.tdd.tp;

public class TddGame {
    private static final int BEGIN = 0;
    private static final int SEND = 1;

    private int state = BEGIN;

    public String processInput(String theInput) {
        String output = null;

        if (state == BEGIN) {
            output = "The name of this Subject is ...";
            state = SEND;
        }
        else if (state == SEND) {
            if (theInput.equalsIgnoreCase("TDD")) {
                output = "YES. YOU WIN. THE GAME START AGAIN...";
                state = BEGIN;
            } else {
                output = "NO. TRY AGAIN";
            }
        }
     return output;
    }
}
