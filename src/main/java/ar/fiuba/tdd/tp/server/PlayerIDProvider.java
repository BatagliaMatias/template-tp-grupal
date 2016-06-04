package ar.fiuba.tdd.tp.server;

import java.util.LinkedList;
import java.util.Queue;

public class PlayerIDProvider {

    private static PlayerIDProvider instance = null;
    private int lastID = 0;
    private Queue<Integer> freeIDs = new LinkedList<Integer>();

    protected PlayerIDProvider() {
    }

    public static PlayerIDProvider getInstance() {
        if (instance == null) {
            instance = new PlayerIDProvider();
        }
        return instance;
    }

    public int getID() {
        if (!freeIDs.isEmpty()) {
            return freeIDs.remove();
        } else {
            lastID++;
            return lastID;
        }
    }

    public void freeID(int id) {
        if (!freeIDs.contains(id)) {
            freeIDs.add(id);
        }
    }

}