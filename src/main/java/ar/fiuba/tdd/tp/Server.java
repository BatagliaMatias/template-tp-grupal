package ar.fiuba.tdd.tp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        //USE: java server portNUmber
        int portNumber = Integer.parseInt(args[0]);

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),"UTF-8"),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),"UTF-8"));

            String inputLine;
            String outputLine;

            // Initiate conversation with client
            TddGame game = new TddGame();
            outputLine = game.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = game.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("YES. YOU WIN. THE GAME START AGAIN...")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Exception on port " + portNumber + "number.");
        }
    }
}
