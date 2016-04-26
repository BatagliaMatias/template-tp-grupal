package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.client.network.ClientNetworkFacade;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {

        //Usage: java 4444 for i.e.
        int portNumber = Integer.parseInt(args[0]);

        try {
            ClientNetworkFacade network = new ClientNetworkFacade();
            network.initConnection(portNumber);

            String fromUser;
            String fromServer;

            while (network.continuesReceivingMessages()) {
                fromServer = network.getLastMessageReceived();
                System.out.println("From Server: " + fromServer);
                if (fromServer.equals("YES. YOU WIN. THE GAME START AGAIN...")) {
                    break;
                }

                fromUser = network.getMessageToSend();
                if (fromUser != null) {
                    System.out.println("From Client: " + fromUser);
                    network.sendMessage(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Bad host ");
            System.exit(1);
        }
    }
}
