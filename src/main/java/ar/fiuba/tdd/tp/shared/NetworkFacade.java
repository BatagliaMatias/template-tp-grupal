package ar.fiuba.tdd.tp.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jorlando on 26/04/16.
 */
public abstract class NetworkFacade {

    protected static final String ENCODING = "UTF-8";
    public String lastMessageReceived = null;
    protected PrintWriter outputStream;
    protected BufferedReader inputStream;

    public NetworkFacade() {
    }

    public abstract void initConnection(ConnectionConfig connection) throws IOException;

    public String receiveMessage() {
        try {
            this.lastMessageReceived = inputStream.readLine();
        } catch (IOException e) {
            this.lastMessageReceived = null;
        }
        return this.lastMessageReceived;
    }

    public void sendMessage(String msg) {
        outputStream.println(msg);
    }

    public boolean continuesReceivingMessages() {
        this.receiveMessage();
        return (this.lastMessageReceived != null);
    }

    public String getLastMessageReceived() {
        return this.lastMessageReceived;
    }

    public void messageToStandardOutput(String message) {
        System.out.println(">> ".concat(message));
    }
}
