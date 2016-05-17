package ar.fiuba.tdd.tp.engine.motor.commands;

import ar.fiuba.tdd.tp.engine.motor.uses.Closable;

public class Close extends GameCommand {

    private Closable target;

    public Close(Closable target) {
        super("close " + target.getName(), target.getName());
        this.target = target;
    }

    public String execute() {
        return target.close();
    }
}