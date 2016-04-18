package ar.fiuba.tdd.tp;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args){
        if (args.length != 1) {
            System.err.println("Usage: > java Server <port> ->init a Server in this port");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        System.out.print(port);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine, outputLine;

            // Conect with client
            TddGame game = new TddGame();
            outputLine = game.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = game.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("YES. YOU WIN. THE GAME START AGAIN..."))
                    break;//FInish conection when client won
            }
        } catch (IOException e) {
            System.out.println("Error to conect");
            System.out.println(e.getMessage());
        }


    }
}
