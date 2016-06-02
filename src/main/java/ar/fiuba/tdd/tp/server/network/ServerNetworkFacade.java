package ar.fiuba.tdd.tp.server.network;


import ar.fiuba.tdd.tp.shared.network.ConnectionConfig;
import ar.fiuba.tdd.tp.shared.network.NetworkFacade;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetworkFacade extends NetworkFacade {

    public void initConnection(ConnectionConfig connection) throws IOException {
        ServerSocket listeningSocket = new ServerSocket(connection.getPort());
        Socket clientSocket = listeningSocket.accept();
        outputStream = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), ENCODING), true);
        this.startReader(clientSocket);
    }

}
