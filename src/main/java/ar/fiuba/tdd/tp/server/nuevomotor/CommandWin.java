package ar.fiuba.tdd.tp.server.nuevomotor;


import java.util.HashMap;

public class CommandWin {

    private HashMap<String, Container> componentes = new HashMap<String, Container>();
    Winnable command;

    public void setWinnableCommand(Winnable command){
        this.command = command;
    }
    public void setComponent(String name,Container component){
        this.componentes.put(name,component);
    }
    public boolean win(){
        return this.command.win(this.componentes);
    }
}
