package ar.fiuba.tdd.tp.client.network;

import ar.fiuba.tdd.tp.shared.network.ConnectionConfig;
import ar.fiuba.tdd.tp.shared.Message;
import ar.fiuba.tdd.tp.shared.network.NetworkFacade;
import ar.fiuba.tdd.tp.shared.network.NetworkReader;

import java.io.*;
import java.net.Socket;

/**
 * Created by jorlando on 26/04/16.
 */
public class ClientNetworkFacade extends NetworkFacade {

    Socket socket = null;

    public void initConnection(ConnectionConfig connection) throws IOException {
        socket = new Socket(connection.getHostName(), connection.getPort());
        outputStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), ENCODING), true);
        this.startReader(socket);
    }

    public void endConnection() {
        try {
            this.sendMessage(Message.EXIT_MESSAGE.getText());
            socket.close();
            outputStream.close();
            this.reader.end();
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }
    }
}
