package ar.fiuba.tdd.tp;

public class Close extends GameCommand {

    private Openable target;

    public Close(Openable target) {
        super("close " + target.getName());
        this.target = target;
    }

    public String execute() {
        return target.close();
    }
}