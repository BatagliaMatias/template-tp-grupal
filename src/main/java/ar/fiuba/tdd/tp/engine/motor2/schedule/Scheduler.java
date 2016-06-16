package ar.fiuba.tdd.tp.engine.motor2.schedule;

import ar.fiuba.tdd.tp.engine.motor2.Container;
import ar.fiuba.tdd.tp.engine.motor2.Game;


import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by jorlando on 11/06/16.
 */
public class Scheduler extends Thread {

    ArrayList<Job> jobs;
    public Clock clock = null;
    Game game = null;
    public long lastRun;
    long cycles;

    public Scheduler(Game game) {
        this.jobs = new ArrayList<>();
        this.game = game;
        this.clock = new Clock();
        this.lastRun = this.clock.actualTimeInMinutes();
        this.cycles = 0;
    }

    public void addJob(Job newJob) {
        jobs.add(newJob);
    }

    public synchronized void run() {
        while (true) {
            this.cycles++;
            if (this.hasToRun()) {
                try{
                    this.iterateJobs();
                } catch (Exception e) {
                    System.out.println("");
                }
            }
        }
    }

    public void iterateJobs() {
        for (Job job : this.jobs) {
            if (job.hasToExecute(this.clock)) {
                this.game.sendMessageToAll(job.execute());
                for (Container player : this.game.getPlayers()) {
                    this.game.checkLoseCondition(player);
                }
            }

        }

    }

    public synchronized boolean hasToRun() {
        this.clock.reload();
        long actual = this.clock.actualTimeInMinutes();
        if (this.lastRun != actual) {
            this.lastRun = actual;
            return true;
        }

        return false;
    }

    public void setLastRun(long newLast) {
        this.lastRun = newLast;
    }
}
