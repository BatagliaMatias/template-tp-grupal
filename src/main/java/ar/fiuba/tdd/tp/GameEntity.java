package ar.fiuba.tdd.tp;

public abstract class GameEntity {
    private String name;

    protected GameEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}