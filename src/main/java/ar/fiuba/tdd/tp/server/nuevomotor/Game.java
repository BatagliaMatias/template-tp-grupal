package ar.fiuba.tdd.tp.server.nuevomotor;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private ArrayList<CommandWin> winnersCommands = new ArrayList<CommandWin>();
    private HashMap<String, Command> executableCommands = new HashMap<String, Command>();

    public void setWinnersCommands(CommandWin commandWin) {
        this.winnersCommands.add(commandWin);
    }

    public void setExecutableCommands(Command command) {
        this.executableCommands.put(command.getName(),command);
    }

    public String execute(String condition) {
        return this.executableCommands.get(condition).execute();
    }

    public boolean win() {
        for (CommandWin command : this.winnersCommands) {
            if (!command.win()) {
                return false;
            }
        }
        return true;
    }
}
