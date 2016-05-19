package ar.fiuba.tdd.tp.client.config;

/**
 * Created by jorlando on 27/04/16.
 */
public enum ClientState {
    RUNNING("running", true),
    WAITING("wainting", false),
    ENDED("ended", false);

    private final String name;
    private final boolean connected;

    ClientState(String newName, boolean connec) {
        this.name = newName;
        this.connected = connec;
    }

    public boolean isConnected() {
        return this.connected;
    }

}
