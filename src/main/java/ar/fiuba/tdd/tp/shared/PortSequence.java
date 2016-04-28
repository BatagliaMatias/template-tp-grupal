package ar.fiuba.tdd.tp.shared;

/**
 * Created by jorlando on 28/04/16.
 */
public class PortSequence {

    public int actualPort = 4444;
    private static PortSequence instance = null;

    protected PortSequence() {
    }

    public int getPort() {
        this.actualPort = (this.actualPort + 1);
        return this.actualPort;
    }

    public static PortSequence getInstance() {
        if (instance == null) {
            instance = new PortSequence();
        }
        return instance;
    }
}
