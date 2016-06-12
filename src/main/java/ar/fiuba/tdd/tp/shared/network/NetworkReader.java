package ar.fiuba.tdd.tp.shared.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by jorlando on 01/06/16.
 */
public class NetworkReader extends Thread {

    public String lastMessageReceived = null;
    protected BufferedReader inputStream;
    protected NetworkState state = NetworkState.CONNECTED;

    public NetworkReader(Socket socket) throws IOException {
        inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    }

    public String read() {
        try {
            this.lastMessageReceived = inputStream.readLine();
            this.messageToStandardOutput(this.lastMessageReceived);
        } catch (IOException | NullPointerException e) {
            this.lastMessageReceived = null;
            state = NetworkState.ENDED;
            System.out.println("TERMINE EL READ NetworkReader");
        }
        return this.lastMessageReceived;
    }

    public void run() {
        while (this.continuesReceivingMessages()) {
            this.read();
        }
    }

    public void messageToStandardOutput(String message) {
        System.out.println(">> Received -> ".concat(message));
    }

    public void end() {
        try {
            this.inputStream.close();
        } catch (IOException e) {
            System.out.print("ERROR: Closing connection Reader");
        }
    }

    public String getLastMessageReceived() {
        return this.lastMessageReceived;
    }

    public void cleanMessage() {
        this.lastMessageReceived = null;
    }

    public boolean continuesReceivingMessages() {
        return (this.state != NetworkState.ENDED);
    }
}
