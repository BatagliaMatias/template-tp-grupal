package ar.fiuba.tdd.tp;

public class Server {
    public static void main(String[] args){
        if (args.length != 1) {
            System.err.println("Usage: > java Server <port> ->init a Server in this port");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        System.out.print(port);
    }
}
