package ar.fiuba.tdd.tp.server.motor.entities;

import java.util.Vector;

public class StackHanoi extends GameEntity {

    private static final int NUMBERDISKS = 8;
    private Vector<Disk> disks;

    public StackHanoi(String name) {

        super(name);

        this.disks = new Vector<Disk>();

    }

    public void putDisks() {

        for (int sizeDisk = NUMBERDISKS; sizeDisk > 0; sizeDisk--) {

            this.disks.add(new Disk(sizeDisk));

        }

    }

    public int checkSizeTop() {

        //Si esta vacio se pueden ingresar discos
        if (this.isEmpty()) {
            return NUMBERDISKS + 1;
        }

        return this.disks.lastElement().getSize();

    }

    public Disk getTop() {

        Disk disk = this.disks.lastElement();

        this.disks.remove(disk);

        return disk;

    }

    public void setDisk(Disk disk) {

        this.disks.add(disk);

    }

    public boolean isComplete() {

        return (this.disks.size() == NUMBERDISKS);

    }

    public boolean isEmpty() {

        return this.disks.isEmpty();

    }

}
