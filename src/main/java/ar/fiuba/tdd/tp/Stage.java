package ar.fiuba.tdd.tp;

public class Stage implements Nameable {
    private String name;

    public Stage(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
