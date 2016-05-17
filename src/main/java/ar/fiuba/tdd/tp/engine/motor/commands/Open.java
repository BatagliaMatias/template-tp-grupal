package ar.fiuba.tdd.tp.engine.motor.commands;

import ar.fiuba.tdd.tp.engine.motor.uses.Openable;

public class Open extends GameCommand {

    protected final Openable target;

    public Open(Openable target) {
        super("open " + target.getName(), target.getName());
        this.target = target;
    }

    public String execute() {
        return target.open();
    }

}