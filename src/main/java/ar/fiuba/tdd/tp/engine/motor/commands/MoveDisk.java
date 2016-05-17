package ar.fiuba.tdd.tp.engine.motor.commands;


import ar.fiuba.tdd.tp.engine.motor.entities.StackHanoi;

public class MoveDisk extends GameCommand {

    private StackHanoi origin;
    private StackHanoi destination;

    public MoveDisk(StackHanoi origin, StackHanoi destination) {

        super("move top stack " + origin.getName() + " stack " + destination.getName(), origin.getName());

        this.origin = origin;
        this.destination = destination;

    }

    @Override
    public String execute() {

        if (this.destination.checkSizeTop() > this.origin.checkSizeTop()) {

            this.destination.setDisk(this.origin.getTop());

            return "moved!";

        }

        return "The disk in stack " + this.origin.getName() + " is greater than disk in stack " + this.destination.getName();

    }

}
