package ar.fiuba.tdd.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jorlando on 26/04/16.
 */
public class ClientNetworkFacade extends NetworkFacade {

    private static final String HOST_NAME = "localhost";
    private static final String ENCODING = "UTF-8";
    private BufferedReader standardInput;

    public void initConnection(int port) throws IOException {
        Socket socket = new Socket(HOST_NAME, port);
        outputStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), ENCODING), true);
        inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENCODING));
        standardInput = new BufferedReader(new InputStreamReader(System.in, ENCODING));
    }

    public String getMessageToSend() throws IOException {
        return standardInput.readLine();
    }

}
