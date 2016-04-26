package ar.fiuba.tdd.tp;

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