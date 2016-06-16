package ar.fiuba.tdd.tp.engine.motor2.schedule;

import java.util.Date;

/**
 * Created by jorlando on 11/06/16.
 */
public class Clock {

    long initialDateInMinutes = 0;
    long nowDateInMinutes = 0;

    public Clock() {
        this.initialDateInMinutes = this.actualTimeInMinutes();
        this.nowDateInMinutes = this.actualTimeInMinutes();
    }

    // interval: intervalo en minutos
    public Boolean isTheIntervalElapsed(long interval) {
        if (this.notInit() && (((this.nowDateInMinutes - this.initialDateInMinutes) % interval) == 0)) {
            return true;
        }
        return false;
    }

    public Boolean notInit() {
        return (this.nowDateInMinutes > this.initialDateInMinutes);
    }

    public long actualTimeInMinutes() {
        return (long)Math.floor((((new Date()).getTime()) / (double)(60000)));
    }

    public void reload() {
        this.nowDateInMinutes = this.actualTimeInMinutes();
    }

    public void setNowDateInMinutes(long timeInit) {
        this.nowDateInMinutes = timeInit;
    }

    public void setInitialDateInMinutes(long timeInit) {
        this.initialDateInMinutes = timeInit;
    }
}
