package ar.fiuba.tdd.tp.engine.motor2;

import java.util.Random;

public class RandomGenerator {

    private Random random = new Random();

    public double getRandomNumber() {
        return Math.random();
    }

    public int getRandomInt(int upperLimit) {
        return random.nextInt(upperLimit);
    }
}
