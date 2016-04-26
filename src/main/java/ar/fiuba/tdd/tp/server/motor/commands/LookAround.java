package ar.fiuba.tdd.tp.server.motor.commands;

import ar.fiuba.tdd.tp.server.motor.uses.Nameable;

import java.util.List;

public class LookAround extends GameCommand {

    private List<? extends Nameable> targets;

    public LookAround(List<? extends Nameable> targets) {
        super("look around");
        this.targets = targets;
    }

    public String execute() {
        String result = "";
        if (targets.size() == 0) {
            result = "There is nothing around";
        } else {
            for (Nameable nameable : targets) {
                StringBuffer buf = new StringBuffer();
                buf.append("There is a ");
                buf.append(nameable.getName());
                buf.append(".");
                result = buf.toString();
            }
        }
        return result;
    }
}
