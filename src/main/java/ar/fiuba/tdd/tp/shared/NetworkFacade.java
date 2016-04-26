package ar.fiuba.tdd.tp.shared;

import java.io.*;

/**
 * Created by jorlando on 26/04/16.
 */
public abstract class NetworkFacade {

    protected PrintWriter outputStream;
    protected BufferedReader inputStream;
    public String lastMessageReceived = null;
    protected static final String ENCODING = "UTF-8";

    public NetworkFacade() {
    }

    public abstract void initConnection(int port) throws IOException;

    public String receiveMessage() throws IOException {
        return inputStream.readLine();
    }

    public void sendMessage(String msg) {
        outputStream.println(msg);
    }

    public boolean continuesReceivingMessages() {
        try {
            this.lastMessageReceived = this.receiveMessage();
        } catch (IOException io) {
            return false;
        }
        return (this.lastMessageReceived != null);
    }

    public String getLastMessageReceived() {
        return this.lastMessageReceived;
    }
}
