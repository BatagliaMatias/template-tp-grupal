package ar.fiuba.tdd.tp.engine.motor2.schedule;

import ar.fiuba.tdd.tp.engine.motor2.Container;
import ar.fiuba.tdd.tp.engine.motor2.Game;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jorlando on 11/06/16.
 */
public class Scheduler {

    ArrayList<Job> jobs = new ArrayList<>();
    long timeSchedule = 1000;
    Clock clock = null;

    public Scheduler(Game game) {
        this.clock = new Clock();
        Timer timer = new Timer();
        TimerTask eventTask = new TimerTask() {
            @Override
            public void run() {
                clock.reload();
                for (Job job : jobs) {
                    if (job.hasToExecute(clock)) {
                        game.sendMessageToAll(job.execute());
                        for (Container player : game.getPlayers()) {
                            game.checkLoseCondition(player);
                        }
                    }
                }
            }
        };
        timer.schedule(eventTask, this.timeSchedule, this.timeSchedule);
    }

    public void addJob(Job newJob) {
        jobs.add(newJob);
    }

    public Clock getClock() {
        return this.clock;
    }
}
