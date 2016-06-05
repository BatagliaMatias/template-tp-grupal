package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameState;

import java.io.*;
import java.net.Socket;

public class PlayerConnection extends Thread {

    private static final String END_CONNECTION_MESSAGE = "El juego ha terminado, has sido desconectado";
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private Game game;
    private int id;
    private PlayerIDProvider idProvider;

    public PlayerConnection(Socket socket, PlayerIDProvider idProvider, Game game, String welcomeMessage) {
        this.game = game;
        this.id = idProvider.getID();
        this.idProvider = idProvider;
        this.socket = socket;
        try {
            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            output.println(welcomeMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String incomingMessage = "";
        while ((game.getState() != GameState.Lost) && (game.getState() != GameState.Won) && (incomingMessage != null)) {
            try {
                incomingMessage = input.readLine();
                if (incomingMessage.equalsIgnoreCase("disconnect")) {
                    endConnection();
                }
                System.out.println("Cliente " + id + " mando mensaje: " + incomingMessage);
                game.process(incomingMessage,id);
                //TEMPORAL
            } catch (Exception e) {
                incomingMessage = null;
            }
        }
        endConnection();
    }

    public void endConnection() {
        try {
            output.println(END_CONNECTION_MESSAGE);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        idProvider.freeID(id);
        System.out.println("Disconnected client " + id);
    }

    public void sendMessage(String message) {
        if (this.socket.isConnected()) {
            //System.out.println("Enviando mensaje: " + message);
            output.println(message);
        }
    }

    public int getID() {
        return id;
    }
}
