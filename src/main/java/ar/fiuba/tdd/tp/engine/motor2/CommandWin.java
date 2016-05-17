package ar.fiuba.tdd.tp.engine.motor2;


import java.util.HashMap;

public class CommandWin {

    private HashMap<String, Container> componentes = new HashMap<String, Container>();
    Winnable command;

    public void setWinnableCommand(Winnable command) {
        this.command = command;
    }

    public void setComponent(Container component) {
        this.componentes.put(component.getName(), component);
    }

    public boolean win() {
        return this.command.win(this.componentes);
    }
}
