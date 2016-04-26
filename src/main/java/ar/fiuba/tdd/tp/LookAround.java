package ar.fiuba.tdd.tp;

import java.util.List;

public class LookAround extends GameCommand {

    private List<? extends Nameable> targets;

    public LookAround(List<? extends Nameable>targets) {
        super("look around");
        this.targets = targets;
    }

    public String execute() {
        String result = "";
        if (targets.size() == 0) {
            result = "There is nothing around";
        } else {
            for (Nameable nameable : targets) {
                result += "There is a " + nameable.getName() + ".";
            }
        }
        return result;
    }
}
