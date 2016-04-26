package ar.fiuba.tdd.tp.server.motor.commands;

import ar.fiuba.tdd.tp.server.motor.uses.Openable;

public class Open extends GameCommand {

    private Openable target;

    public Open(Openable target) {
        super("open " + target.getName());
        this.target = target;
    }

    public String execute() {
        return target.open();
    }
}