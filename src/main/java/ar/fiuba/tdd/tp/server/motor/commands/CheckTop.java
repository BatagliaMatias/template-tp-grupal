package ar.fiuba.tdd.tp.server.motor.commands;

import ar.fiuba.tdd.tp.server.motor.entities.StackHanoi;

public class CheckTop extends GameCommand {

    private StackHanoi stackHanoi;

    public CheckTop(StackHanoi stackHanoi) {

        super("check top stack " + stackHanoi.getName());
        this.stackHanoi = stackHanoi;

    }

    @Override
    public String execute() {

        if (this.stackHanoi.isEmpty()) {

            return "The stack " + this.stackHanoi.getName() + " is empty";

        }

        return "Size of top from stack " + this.stackHanoi.getName() + " is " + this.stackHanoi.checkSizeTop();

    }
}
