package ar.fiuba.tdd.tp;

public class Close extends GameCommand {

    private Closable target;

    public Close(Closable target) {
        super("close " + target.getName());
        this.target = target;
    }

    public String execute() {
        return target.close();
    }
}