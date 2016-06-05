package ar.fiuba.tdd.tp.engine.motor2;

import ar.fiuba.tdd.tp.server.PlayerConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command";
    GameState state = GameState.Ready;
    private ArrayList<CommandWin> winnersCommands = new ArrayList<CommandWin>();
    private HashMap<String, Command> executableCommands = new HashMap<String, Command>();
    private ArrayList<PlayerConnection> players = new ArrayList<PlayerConnection>();
    private Condition winCondition = null;
    private Condition loseCondition = null;

    public void setWinCondition(Condition winCondition) {
        this.winCondition = winCondition;
    }

    public void setLoseCondition(Condition loseCondition) {
        this.loseCondition = loseCondition;
    }

    public void setExecutableCommands(Command command) {
        this.executableCommands.put(command.getName(), command);
    }

    public void setWinnersCommands(CommandWin commandWin) {
        this.winnersCommands.add(commandWin);
    }

    public void addTimedEvent(boolean repeat, long delay, Event event) {
        Timer timer = new Timer();
        TimerTask eventTask = new TimerTask() {
            @Override
            public void run() {
                sendMessageToAll(event.execute());
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

    public void sendMessageToAll(String msg) {
        for (PlayerConnection player : players) {
            player.sendMessage(msg);
        }
    }

    public void sendMessageTo(int id, String msg) {
        for (PlayerConnection player : players) {
            if (player.getID() == id) {
                player.sendMessage(msg);
            }
        }
    }

    public void setCommandWin(Container container, String statusWin) {
        CommandWin win = new CommandWin();
        win.setComponent(container);
        win.setWinnableCommand((HashMap<String, Container> components) -> container.checkStatus(statusWin));
        this.setWinnersCommands(win);
    }

    public void process(String command, int playerID) {
        String result = this.execute(command);

        if (result.equals(INVALID_COMMAND_MESSAGE)) {
            sendMessageTo(playerID, result);
        } else {
            sendMessageToAll("player " + playerID + " has " + result);
        }
        if (endGame()) {
            sendMessageToAll(getFinalMessage());
        }
    }

    public String execute(String condition) {
        this.state = GameState.InProgress;
        try {
            if (this.executableCommands.containsKey(condition)) {
                return this.executableCommands.get(condition).execute();
            } else {
                return INVALID_COMMAND_MESSAGE;
            }
        } finally {
            this.checkIfGameWin();
            this.checkWinCondition();
            this.checkLoseCondition();
        }
    }

    private void checkWinCondition() {
        if (winCondition != null) {
            if (winCondition.applies()) {
                this.state = GameState.Won;
            }
        }
    }

    private void checkLoseCondition() {
        if (loseCondition != null) {
            if (loseCondition.applies()) {
                this.state = GameState.Lost;
            }
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
        if (this.winnersCommands.isEmpty()) {
            return;
        }
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
