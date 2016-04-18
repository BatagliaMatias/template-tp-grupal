package ar.fiuba.tdd.tp;

public class Server {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("To use: java Server <port number>");
            System.exit(1);
        }
    }
}
