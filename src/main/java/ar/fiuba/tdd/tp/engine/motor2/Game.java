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
    private HashMap<String, PlayerCommand> playerCommands = new HashMap<>();
    private ArrayList<PlayerConnection> players = new ArrayList<PlayerConnection>();
    private Condition winCondition = null;
    private Condition loseCondition = null;
    private int lastPlayerToExecute = 0;
    private Container initialPosition = null;
    private int maxPlayers = 10;

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setWinCondition(Condition winCondition) {
        this.winCondition = winCondition;
    }

    public void setLoseCondition(Condition loseCondition) {
        this.loseCondition = loseCondition;
    }

    public void setExecutableCommands(Command command) {
        this.executableCommands.put(command.getName(), command);
    }

    public void setPlayerCommand(PlayerCommand command) {
        this.playerCommands.put(command.getName(), command);
    }

    public void setWinnersCommands(CommandWin commandWin) {
        this.winnersCommands.add(commandWin);
    }

    public void setInitialPosition(Container position) {
        this.initialPosition = position;
    }

    public void addTimedEvent(boolean repeat, long delay, Event event) {
        Timer timer = new Timer();
        TimerTask eventTask = new TimerTask() {
            @Override
            public void run() {
                sendMessageToAll(event.execute());
                for (PlayerConnection player : players) {
                    checkLoseCondition(player.getContainer());
                }
            }
        };
        if (repeat) {
            timer.schedule(eventTask, delay, delay);
        } else {
            timer.schedule(eventTask, delay);
        }

    }



    public void addPlayer(PlayerConnection player) {
        sendMessageToAll("player " + player.getID() + " has entered the game");
        if (initialPosition != null) {
            Container playerContainer = new Container("player" + player.getID());
            initialPosition.setComponent(playerContainer);
            player.setContainer(playerContainer);
        }
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

    public void process(String command, PlayerConnection player) {
        if (checkLookAroundCommand(command, player)) {
            return;
        }
        runNormalCommand(command, player);
    }

    private void runNormalCommand(String command, PlayerConnection player) {
        int playerID = player.getID();
        lastPlayerToExecute = playerID;
        String result = this.execute(command, player);
        sendMessageTo(playerID, result);
        if (!(result.equals(INVALID_COMMAND_MESSAGE))) {
            sendMessageToAll("Player " + playerID + " executed: " + command);
        }
        if (endGame()) {
            sendMessageToAll(getFinalMessage());
        }
    }

    public String execute(String condition, PlayerConnection player) {
        this.state = GameState.InProgress;
        try {
            if (this.executableCommands.containsKey(condition)) {
                return this.executableCommands.get(condition).execute();
            }
            if (this.playerCommands.containsKey(condition)) {
                return this.playerCommands.get(condition).execute(player.getContainer());
            }
            return INVALID_COMMAND_MESSAGE;
        } finally {
            this.checkIfGameWin();
            this.checkWinCondition(player.getContainer());
            this.checkLoseCondition(player.getContainer());
        }
    }

    private void checkWinCondition(Container player) {
        if (winCondition != null) {
            if (winCondition.applies(player)) {
                this.state = GameState.Won;
            }
        }
    }

    private void checkLoseCondition(Container player) {
        if (loseCondition != null) {
            if (loseCondition.applies(player)) {
                for (PlayerConnection playerConnection : players) {
                    if (playerConnection.getContainer() == player) {
                        sendMessageToAll("Perdio: " + player.getName());
                        playerConnection.endConnection();
                        players.remove(playerConnection);
                    }
                }

            }
        }
    }

    public boolean endGame() {
        return ((this.state == GameState.Won) || (this.state == GameState.Lost));
    }

    public ArrayList<Container> getPlayers() {
        ArrayList<Container> playersContainers = new ArrayList<>();
        for (PlayerConnection playerConnection : players) {
            playersContainers.add(playerConnection.getContainer());
        }
        return  playersContainers;
    }

    public String getFinalMessage() {
        if (lastPlayerToExecute > 0) {
            return "Player " + lastPlayerToExecute + " has " + this.state.getMessage();
        }
        return this.state.getMessage();
    }

    public boolean checkLookAroundCommand(String cmd, PlayerConnection player) {
        if ((!cmd.equalsIgnoreCase("look around")) || (player.getContainer() == null)) {
            return false;
        }
        Container playerContainer = player.getContainer();
        StringBuffer buf = new StringBuffer();
        for (Container container : playerContainer.getParent().getComponents()) {
            buf.append(" Theres is a " + container.getName());
        }
        String res = buf.toString();
        sendMessageTo(player.getID(), res);
        return true;
    }

    public void removePlayer(PlayerConnection player) {
        sendMessageToAll("Player " + player.getID() + " has left the game");
        players.remove(player);
        Container playerContainer = player.getContainer();
        if (playerContainer != null) {
            playerContainer.getParent().removeComponent(playerContainer);
        }
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

    public String execute(String condition) {
        this.state = GameState.InProgress;
        try {
            if (this.executableCommands.containsKey(condition)) {
                return this.executableCommands.get(condition).execute();
            }
            return INVALID_COMMAND_MESSAGE;
        } finally {
            this.checkIfGameWin();
        }
    }

    public void loseGame() {
        this.state = GameState.Lost;
    }

    public void loseGame(Container player) {
        for (PlayerConnection playerConnection : players) {
            if (playerConnection.getContainer() == player) {
                removePlayer(playerConnection);
            }
        }
    }

    public boolean isFull() {
        return players.size() >= maxPlayers;
    }
}
