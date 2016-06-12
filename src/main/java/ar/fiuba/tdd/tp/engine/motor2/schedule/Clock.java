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
    }

    public Boolean isTheIntervalElapsed(long interval) {
        if ((this.nowDateInMinutes > this.initialDateInMinutes) && ((this.nowDateInMinutes % interval) == 0)) {
            return true;
        }
        return false;
    }

    public long actualTimeInMinutes() {
        return (long)Math.floor((((new Date()).getTime()) / (60000)))*1000;
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
