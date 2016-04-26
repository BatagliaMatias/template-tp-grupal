package ar.fiuba.tdd.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jorlando on 26/04/16.
 */
public abstract class NetworkFacade {

    PrintWriter outputStream;
    BufferedReader inputStream;
    String lastMessageReceived = null;

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
