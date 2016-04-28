package ar.fiuba.tdd.tp.shared;

/**
 * Created by jorlando on 27/04/16.
 */
public class ConnectionConfig {

    String hostName = "localhost";
    int port = 4444;

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
