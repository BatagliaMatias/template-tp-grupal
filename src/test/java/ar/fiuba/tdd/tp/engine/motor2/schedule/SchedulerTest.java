package ar.fiuba.tdd.tp.engine.motor2.schedule;

import ar.fiuba.tdd.tp.engine.motor2.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by jorlando on 15/06/16.
 */
public class SchedulerTest {

    Scheduler scheduler;
    int jobExecutions = 0;
    Job job = new Job(false, 2, () -> {
            jobExecutions++;
            return "test";
        });

    @Before
    public void setUp() {
        this.scheduler = new Scheduler(new Game());
    }

    @After
    public void cleanUp() {
        this.scheduler.stop();
    }

    @Test
    public void testConstructor() {
        assertNotEquals(this.scheduler.game,null);
        assertNotEquals(this.scheduler.clock,null);
        assertTrue(this.scheduler.clock.actualTimeInMinutes() > 0);
        assertEquals(this.scheduler.clock.actualTimeInMinutes(), this.scheduler.lastRun);
        assertEquals(0, this.scheduler.jobs.size());
    }

    @Test
    public void testAddJob() {
        this.scheduler.addJob(job);
        assertEquals(1,this.scheduler.jobs.size());
        assertEquals(job, this.scheduler.jobs.get(0));
    }

    @Test
    public void testHasToRunInitFalse() {
        assertFalse(this.scheduler.hasToRun());
    }

    @Test
    public void testHasToRunModifyingLastRunTrue() {
        this.scheduler.lastRun = (this.scheduler.clock.actualTimeInMinutes() - 2);
        assertTrue(this.scheduler.hasToRun());
    }

    @Test
    public void testRunNotExecuteJob() {
        this.scheduler.addJob(job);
        this.scheduler.start();
        this.sleep();
        assertEquals(0, this.jobExecutions);
        assertTrue(this.scheduler.cycles > 0);
    }

    @Test
    public void testRunExecuteJob() {
        this.scheduler.start();
        this.scheduler.addJob(job);
        //Seteo la hora inicial hace dos minutos
        this.scheduler.clock.setInitialDateInMinutes((this.scheduler.clock.actualTimeInMinutes() - 4));
        //Seteo que corrio hace dos minutos, el job deberia correr cada un minuto
        this.scheduler.lastRun = (this.scheduler.clock.actualTimeInMinutes() - 4);

        this.sleep();
        assertEquals(1, this.jobExecutions);
        assertTrue(this.scheduler.cycles > 0);
    }

    public void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
