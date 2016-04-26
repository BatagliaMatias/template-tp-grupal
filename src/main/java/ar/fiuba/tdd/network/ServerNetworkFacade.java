package ar.fiuba.tdd.network;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetworkFacade extends NetworkFacade {

    public void initConnection(int port) throws IOException {
        ServerSocket listeningSocket = new ServerSocket(port);
        Socket clientSocket = listeningSocket.accept();
        outputStream = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), ENCODING), true);
        inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), ENCODING));
    }

}
