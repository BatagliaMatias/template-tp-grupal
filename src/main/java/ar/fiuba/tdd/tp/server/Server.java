package ar.fiuba.tdd.tp.server;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerHelper server = new ServerHelper();
        server.init();
    }
}
