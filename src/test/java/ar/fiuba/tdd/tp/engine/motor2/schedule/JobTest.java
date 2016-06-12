package ar.fiuba.tdd.tp.engine.motor2.schedule;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by jorlando on 11/06/16.
 */
public class JobTest {

    Job job = null;
    Clock clock = null;
    Boolean repeatable = false;
    long interval = 2000;

    @Before
    public void setUp() {
        this.job = new Job(repeatable, interval, () -> { return "test"; });
        this.clock = new Clock();
        this.clock.reload();
    }

    @Test
    public void testConstruct() {
        assertEquals(this.repeatable, job.repeatable);
        assertEquals(this.interval, job.interval);
        assertEquals(0, job.quantityExecutions);
    }

    @Test
    public void testExecute() {
        assertEquals("test",this.job.execute());
        assertEquals(1, job.quantityExecutions);
    }

    @Test
    public void testAlreadyExecutedFirstExecution() {
        assertFalse(this.job.alreadyExecuted());
    }

    @Test
    public void testAlreadyExecutedSecondExecution() {
        this.job.execute();
        assertTrue(this.job.alreadyExecuted());
    }

    @Test
    public void testHasToExecuteFirstExecutionTrueRepeatable() {
        Job jobTest = new Job(true, interval, ()-> { return "test"; });
        this.clock.setNowDateInMinutes(this.clock.initialDateInMinutes+2000);
        assertTrue(jobTest.hasToExecute(this.clock));
    }

    @Test
    public void testHasToExecuteFirstExecutionFalseRepeatable() {
        Job jobTest = new Job(false, interval, ()-> { return "test"; });
        this.clock.setNowDateInMinutes(this.clock.initialDateInMinutes+2000);
        assertTrue(jobTest.hasToExecute(this.clock));
    }

    @Test
    public void testHasToExecuteSecondExecutionTrueRepeatable() {
        Job jobTest = new Job(true, interval, ()-> { return "test"; });
        jobTest.execute();
        this.clock.setNowDateInMinutes(this.clock.initialDateInMinutes+2000);
        assertTrue(jobTest.hasToExecute(this.clock));
    }

    @Test
    public void testHasToExecuteSecondExecutionFalseRepeatable() {
        Job jobTest = new Job(false, interval, ()-> { return "test"; });
        jobTest.execute();
        this.clock.setNowDateInMinutes(this.clock.initialDateInMinutes+2000);
        assertFalse(jobTest.hasToExecute(this.clock));
    }

}
