package ar.fiuba.tdd.tp.engine.motor2;

import ar.fiuba.tdd.tp.server.PlayerConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    GameState state = GameState.Ready;
    private ArrayList<CommandWin> winnersCommands = new ArrayList<CommandWin>();
    private HashMap<String, Command> executableCommands = new HashMap<String, Command>();
    private ArrayList<PlayerConnection> players = new ArrayList<PlayerConnection>();

    public void setWinnersCommands(CommandWin commandWin) {
        this.winnersCommands.add(commandWin);
    }

    public void setExecutableCommands(Command command) {
        this.executableCommands.put(command.getName(), command);
    }

    public void addTimedEvent(boolean repeat, long delay, Event event) {
        Timer timer = new Timer();
        TimerTask eventTask = new TimerTask() {
            @Override
            public void run() {
                sendMessage(event.execute());
            }
        };
        if (repeat) {
            timer.schedule(eventTask, delay, delay);
        } else {
            timer.schedule(eventTask, delay);
        }
    }

    public void addPlayer(PlayerConnection player) {
        players.add(player);
    }

    public void sendMessage(String msg) {
        // TODO
        System.out.println(msg);
    }


    public void setCommandWin(Container container, String statusWin) {
        CommandWin win = new CommandWin();
        win.setComponent(container);
        win.setWinnableCommand((HashMap<String, Container> components) -> container.checkStatus(statusWin));
        this.setWinnersCommands(win);
    }

    public String execute(String condition) {
        this.state = GameState.InProgress;
        try {
            if (this.executableCommands.containsKey(condition)) {
                return this.executableCommands.get(condition).execute();
            } else {
                return "Invalid command: ".concat(condition);
            }
        } finally {
            this.checkIfGameWin();
        }
    }

    public boolean endGame() {
        return ((this.state == GameState.Won) || (this.state == GameState.Lost));
    }

    public String getFinalMessage() {
        return this.state.getMessage();
    }

    public GameState getState() {
        return this.state;
    }

    public void setState(GameState newState) {
        this.state = newState;
    }

    public void checkIfGameWin() {
        for (CommandWin command : this.winnersCommands) {
            if (!command.win()) {
                return;
            }
        }
        this.state = GameState.Won;
    }

    public void loseGame() {
        this.state = GameState.Lost;
    }
}
