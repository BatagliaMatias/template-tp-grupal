package ar.fiuba.tdd.tp.shared.network;

import ar.fiuba.tdd.tp.shared.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by jorlando on 26/04/16.
 */
public abstract class NetworkFacade {

    protected static final String ENCODING = "UTF-8";
    protected PrintWriter outputStream;
    protected NetworkReader reader = null;
    protected Integer msToWait = 500;

    public NetworkFacade() {
    }

    public void startReader(Socket socket) throws IOException {
        this.reader = new NetworkReader(socket);
        this.reader.start();
    }

    public abstract void initConnection(ConnectionConfig connection) throws IOException;

    public void sendMessage(String msg) {
        outputStream.println(msg);
    }

    public boolean continuesReceivingMessages() {
        this.sleep();
        return this.reader.continuesReceivingMessages();
    }

    public String getLastMessageReceived() {
        return this.reader.getLastMessageReceived();
    }

    public void messageToStandardOutput(String message) {
        this.reader.messageToStandardOutput(message);
    }

    public boolean endgameMessageReceived() {
        String message = this.reader.getLastMessageReceived();
        return (message.equals(Message.WIN.getText()) || message.equals(Message.LOST.getText()));
    }

    public void sleep() {
        try {
            Thread.sleep(msToWait);
        } catch (InterruptedException e) {
            System.out.println("Fail sleeping");
        }
    }
}
