package ar.fiuba.tdd.tp.client.config;

/**
 * Created by jorlando on 27/04/16.
 */
public enum GameStates {
    RUNNING("running", true),
    WAITING("wainting", false),
    ENDED("ended", false);

    private final String name;
    private final boolean connected;

    GameStates(String newName, boolean connec) {
        this.name = newName;
        this.connected = connec;
    }

    public boolean isConnected() {
        return this.connected;
    }

}
