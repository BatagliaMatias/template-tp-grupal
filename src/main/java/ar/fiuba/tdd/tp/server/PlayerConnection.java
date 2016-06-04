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

    public PlayerConnection(Socket socket, int id, Game game, String welcomeMessage) {
        this.game = game;
        this.id = id;
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
        while ((game.getState() != GameState.Lost) && (game.getState() != GameState.Won) && (incomingMessage != null) ) {
            try {
                incomingMessage = input.readLine();
                if (incomingMessage.isEmpty()) {
                    endConnection();
                }
                System.out.println("Cliente " + id + " mando mensaje: " + incomingMessage);
                String response = game.execute(incomingMessage);
                //TEMPORAL
                sendMessage(response);
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
        PlayerIDProvider.getInstance().freeID(id);
        System.out.println("Disconnected client " + id);
    }

    public void sendMessage(String message) {
        System.out.println("Enviando mensaje: " + message);
        output.println(message);
    }

    public int getID() {
        return id;
    }
}
