package ar.fiuba.tdd.tp.engine.motor2.schedule;

import ar.fiuba.tdd.tp.engine.motor2.Event;
import ar.fiuba.tdd.tp.engine.motor2.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by jorlando on 15/06/16.
 */
public class SchedulerTest {

    private JobStub job;
    private Scheduler scheduler;

    @Before
    public void setUp() {
        this.job = new JobStub(false, 2, () -> { return "test"; });
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
    public void testRunNotExecuteJob() throws Exception {
        this.scheduler.addJob(job);
        this.scheduler.start();
        boolean timedOut = this.job.waitForJobExecution(1, TimeUnit.SECONDS);
        assertFalse(timedOut);
        assertEquals(0, job.getJobExecutions());
        assertTrue(this.scheduler.cycles > 0);
    }

    @Test
    public void testRunExecuteJob() throws Exception {
        this.scheduler.start();
        this.scheduler.addJob(job);
        //Seteo la hora inicial hace dos minutos
        this.scheduler.clock.setInitialDateInMinutes((this.scheduler.clock.actualTimeInMinutes() - 4));
        //Seteo que corrio hace dos minutos, el job deberia correr cada un minuto
        this.scheduler.lastRun = (this.scheduler.clock.actualTimeInMinutes() - 4);

        this.job.waitForJobExecution();

        assertEquals(1, job.getJobExecutions());
        assertTrue(this.scheduler.cycles > 0);
    }

}

class JobStub extends Job {
    private int jobExecutions = 0;
    private CountDownLatch jobExeuction = new CountDownLatch(1);

    public JobStub(boolean repeat, long delay, Event event) {
        super(repeat, delay, event);
    }

    public String execute() {
        final String result = this.task.execute();
        jobExecutions++;
        jobExeuction.countDown();
        return result;
    }

    public int getJobExecutions() {
        return jobExecutions;
    }

    public void waitForJobExecution() throws InterruptedException {
        jobExeuction.await();
    }

    public boolean waitForJobExecution(long timeout, TimeUnit unit) throws InterruptedException {
        return jobExeuction.await(timeout, unit);
    }
}