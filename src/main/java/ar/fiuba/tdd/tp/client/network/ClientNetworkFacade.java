package ar.fiuba.tdd.tp.client.network;

import ar.fiuba.tdd.tp.shared.NetworkFacade;

import java.io.*;
import java.net.Socket;

/**
 * Created by jorlando on 26/04/16.
 */
public class ClientNetworkFacade extends NetworkFacade {

    private static final String HOST_NAME = "localhost";
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