package ar.fiuba.tdd.tp.shared.network;

/**
 * Created by jorlando on 27/04/16.
 */
public class ConnectionConfig {

    String hostName = "localhost";
    int port = PortSequence.getInstance().getPort();

    public ConnectionConfig(String newHost, int newPort) {
        this.hostName = newHost;
        this.port = newPort;
    }

    public ConnectionConfig() {
    }

    public String getHostName() {
        return this.hostName;
    }

    public int getPort() {
        return this.port;
    }
}
