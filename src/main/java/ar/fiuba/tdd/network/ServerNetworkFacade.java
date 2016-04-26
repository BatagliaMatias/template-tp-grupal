package ar.fiuba.tdd.network;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetworkFacade extends NetworkFacade {

    private Socket clientSocket;
    private ServerSocket listeningSocket;

    public void initConnection(int port) throws IOException {
        listeningSocket = new ServerSocket(port);
        clientSocket = listeningSocket.accept();
        outputStream = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
        inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
    }

}
