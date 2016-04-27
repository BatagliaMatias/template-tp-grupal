package ar.fiuba.tdd.tp.client;


import java.io.IOException;

public class Client {

    public static void main(String[] args) {
        ClientHelper client = new ClientHelper();
        try {
            client.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
