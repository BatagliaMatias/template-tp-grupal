package ar.fiuba.tdd.tp.engine.motor.commands;

import ar.fiuba.tdd.tp.engine.motor.entities.Thief;

public class Talk extends GameCommand {

    private Thief thief;
    private String response;

    public Talk(String talk, String response, Thief thief) {

        super("Talk to thief '" + talk + "'", "thief");

        this.thief = thief;
        this.response = response;

    }

    @Override
    public String execute() {

        this.thief.steal();

        return response + " (The thief has just stolen your object!...)";

    }

}