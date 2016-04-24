package ar.fiuba.tdd.tp;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetworkFacade {
    PrintWriter outputStream;
    BufferedReader inputStream;
    private Socket clientSocket;
    private ServerSocket listeningSocket;

    public ServerNetworkFacade() {
    }

    public void startListening(int port) throws IOException {
        listeningSocket = new ServerSocket(port);
        clientSocket = listeningSocket.accept();
        outputStream = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);
        inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
    }

    public String receiveMessage() throws IOException {
        return inputStream.readLine();
    }

    public void sendMessage(String msg) {
        outputStream.println(msg);
    }


}
