package ar.fiuba.tdd.tp.server.network;


import ar.fiuba.tdd.tp.shared.network.ConnectionConfig;
import ar.fiuba.tdd.tp.shared.network.NetworkFacade;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetworkFacade extends NetworkFacade {

    private ServerSocket listeningSocket;

    public void initConnection(ConnectionConfig connection) throws IOException {
        listeningSocket = new ServerSocket(connection.getPort());
    }

    public Socket acceptClient() throws IOException {
        return listeningSocket.accept();
    }

    public boolean hasMessageToProcess() {
        return (this.getLastMessageReceived() != null);
    }

    public void cleanMessage() {
        this.reader.cleanMessage();
    }

}
