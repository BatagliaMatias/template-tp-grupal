package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.network.ServerNetworkFacade;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {

        //USE: java server portNUmber
        int portNumber = Integer.parseInt(args[0]);

        try {
            ServerNetworkFacade network = new ServerNetworkFacade();
            network.initConnection(portNumber);

            String outputLine;

            // Initiate conversation with client
            Game game = new TddGame();
            outputLine = game.processInput(null);
            network.sendMessage(outputLine);

            while (network.continuesReceivingMessages()) {
                outputLine = game.processInput(network.getLastMessageReceived());
                network.sendMessage(outputLine);
                if (outputLine.equals("YES. YOU WIN. THE GAME START AGAIN...")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Exception on port " + portNumber + "number.");
        }
    }
}
