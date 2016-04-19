package ar.fiuba.tdd.tp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {

        //Usage: java localhost 4444 for i.e.
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try {
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));

            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("From Server: " + fromServer);
                if (fromServer.equals("YES. YOU WIN. THE GAME START AGAIN...")) {
                    break;
                }

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("From Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Bad host: " + hostName);
            System.exit(1);
        }
    }
}
