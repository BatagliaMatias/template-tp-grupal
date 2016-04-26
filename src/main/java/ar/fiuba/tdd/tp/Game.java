package ar.fiuba.tdd.tp;


import java.util.List;

public abstract class Game {
    protected List<GameCommand> commands;

    protected Game(/*List<GameCommand> commands*/) {
        /*this.commands = commands;*/
    }

    /*
    abstract void initializeGame();

    abstract String makePlay(String input);

    abstract boolean endOfGame();

    abstract void endGame();*/

    /* template method : */
    public String processInput(String input) {

        for (GameCommand command : commands) {
            if (input.equals(command.getIdentifier())) {
                return command.execute();
            }
        }
        return "Invalid comand";
    }
}

