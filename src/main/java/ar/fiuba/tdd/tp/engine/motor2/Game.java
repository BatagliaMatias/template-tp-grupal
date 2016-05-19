package ar.fiuba.tdd.tp.engine.motor2;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    GameState state = GameState.READY;
    private ArrayList<CommandWin> winnersCommands = new ArrayList<CommandWin>();
    private HashMap<String, Command> executableCommands = new HashMap<String, Command>();

    public void setWinnersCommands(CommandWin commandWin) {
        this.winnersCommands.add(commandWin);
    }

    public void setExecutableCommands(Command command) {
        this.executableCommands.put(command.getName(),command);
    }

    public void setCommandWin(Container container, String statusWin) {
        CommandWin win = new CommandWin();
        win.setComponent(container);
        win.setWinnableCommand((HashMap<String, Container> components)-> container.checkStatus(statusWin));
        this.setWinnersCommands(win);
    }

    public String execute(String condition) {
        this.state = GameState.IN_PROGRESS;
        if (this.executableCommands.containsKey(condition)) {
            return this.executableCommands.get(condition).execute();
        } else {
            return "Invalid command: ".concat(condition);
        }
    }

    public boolean win() {
        for (CommandWin command : this.winnersCommands) {
            if (!command.win()) {
                return false;
            }
        }
        this.state = GameState.WIN;
        return true;
    }

    public GameState getState() {
        return this.state;
    }
}
