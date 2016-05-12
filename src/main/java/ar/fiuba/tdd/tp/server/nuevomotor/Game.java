package ar.fiuba.tdd.tp.server.nuevomotor;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private ArrayList<CommandWin> winnersCommands = new ArrayList<CommandWin>();
    private HashMap<String, Command> executableCommands = new HashMap<String, Command>();

    public void setWinnersCommands(CommandWin commandWin){
        this.winnersCommands.add(commandWin);
    }

    public void setExecutableCommands(String condition, Command command){
        this.executableCommands.put(condition,command);
    }

    public String excute(String condition){

        return this.executableCommands.get(condition).execute();
    }

    public boolean iWin(){
        boolean win = true;
        for(CommandWin command : this.winnersCommands){
            win = win && command.win();
        }
        return win;
    }
}
