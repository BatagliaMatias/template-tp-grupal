package ar.fiuba.tdd.tp.engine.motor2;

import java.util.ArrayList;
import java.util.Random;

public class RandomGeneratorMock extends RandomGenerator {

    private ArrayList<Integer> ints;
    private ArrayList<Double> doubles;

    public void setGetRandomIntSequence(ArrayList<Integer> ints) {
        this.ints = ints;
    }

    public void setGetRandomNumberSequence(ArrayList<Double> doubles) {
        this.doubles = doubles;
    }

    public double getRandomNumber() {
        return doubles.remove(0);
    }

    public int getRandomInt(int upperLimit) {
        return ints.remove(0);
    }
}
