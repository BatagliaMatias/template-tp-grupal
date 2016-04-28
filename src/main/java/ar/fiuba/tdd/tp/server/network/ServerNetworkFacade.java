package ar.fiuba.tdd.tp.server.network;


import ar.fiuba.tdd.tp.shared.ConnectionConfig;
import ar.fiuba.tdd.tp.shared.NetworkFacade;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetworkFacade extends NetworkFacade {

    public void initConnection(ConnectionConfig connection) throws IOException {
        ServerSocket listeningSocket = new ServerSocket(connection.getPort());
        Socket clientSocket = listeningSocket.accept();
        outputStream = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), ENCODING), true);
        inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), ENCODING));
    }

}
