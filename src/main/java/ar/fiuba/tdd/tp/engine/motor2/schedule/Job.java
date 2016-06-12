package ar.fiuba.tdd.tp.engine.motor2.schedule;

import ar.fiuba.tdd.tp.engine.motor2.Event;

/**
 * Created by jorlando on 11/06/16.
 */
public class Job {

    boolean repeatable = false;
    long interval = 0;
    Event task;
    int quantityExecutions;

    public Job(boolean repeat, long delay, Event event) {
        this.repeatable = repeat;
        this.interval = delay;
        this.task = event;
        this.quantityExecutions = 0;
    }

    public String execute() {
        this.quantityExecutions++;
        return this.task.execute();
    }

    public boolean hasToExecute(Clock clock) {
        if (this.alreadyExecuted() && !this.repeatable) {
            return false;
        }
        return clock.isTheIntervalElapsed(this.interval);
    }

    public boolean alreadyExecuted() {
        return (this.quantityExecutions > 0);
    }
}
