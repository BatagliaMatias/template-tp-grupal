package ar.fiuba.tdd.tp;

public class Pick extends GameCommand {

    private Pickable target;

    public Pick(Pickable target) {
        super("pick " + target.getName());
        this.target = target;
    }

    public String execute() {
        return target.pick();
    }
}