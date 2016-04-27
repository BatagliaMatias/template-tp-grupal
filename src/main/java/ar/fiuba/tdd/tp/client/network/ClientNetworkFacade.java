package ar.fiuba.tdd.tp.client.network;

import ar.fiuba.tdd.tp.shared.ConnectionConfig;
import ar.fiuba.tdd.tp.shared.Message;
import ar.fiuba.tdd.tp.shared.NetworkFacade;

import java.io.*;
import java.net.Socket;

/**
 * Created by jorlando on 26/04/16.
 */
public class ClientNetworkFacade extends NetworkFacade {

    private BufferedReader standardInput;
    Socket socket = null;

    public void initConnection(ConnectionConfig connection) throws IOException {
        socket = new Socket(connection.getHostName(), connection.getPort());
        outputStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), ENCODING), true);
        inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENCODING));
        standardInput = new BufferedReader(new InputStreamReader(System.in, ENCODING));
        this.messageToStandardOutput(this.receiveMessage());
    }

    public String getMessageToSend() throws IOException {
        return standardInput.readLine();
    }

    public void endConnection() {
        this.sendMessage(Message.EXIT_MESSAGE.getText());
        try {
            this.messageToStandardOutput(this.receiveMessage());
            socket.close();
            outputStream.close();
            inputStream.close();
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }
    }

}
