package ar.fiuba.tdd.tp;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {

        //USE: java server portNUmber
        int portNumber = Integer.parseInt(args[0]);

        try {
            ServerNetworkFacade network = new ServerNetworkFacade();
            network.startListening(portNumber);

            String inputLine;
            String outputLine;

            // Initiate conversation with client
            Motor motor = new Motor();
            network.sendMessage("Hola");

            while ((inputLine = network.receiveMessage()) != null) {
                outputLine = motor.processInput(inputLine);
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
