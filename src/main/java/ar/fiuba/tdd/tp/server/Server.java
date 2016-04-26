package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.server.motor.Motor;
import ar.fiuba.tdd.tp.server.network.ServerNetworkFacade;

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
            Motor motor = new Motor();
            network.sendMessage("Hola, esto es el juego AbrirPuerta2");

            while (network.continuesReceivingMessages()) {
                outputLine = motor.processInput(network.getLastMessageReceived());
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
