package ar.fiuba.tdd.tp.engine.motor2.schedule;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by jorlando on 12/06/16.
 */
public class ClockTest {

    Clock clock = null;


    @Before
    public void setUp() {
        this.clock = new Clock();
    }

    @Test
    public void testActualTimeInMinutes() {
        long dateInMinute = (long) Math.floor((((new Date()).getTime()) / (long)(60000)));
        assertEquals(dateInMinute, this.clock.actualTimeInMinutes());
    }

    @Test
    public void testReload() {
        this.clock.setNowDateInMinutes(0);
        assertEquals(0, this.clock.nowDateInMinutes);
        this.clock.reload();
        assertNotEquals(0, this.clock.nowDateInMinutes);
    }

    @Test
    public void testSetNowDateInMinutes() {
        this.clock.setNowDateInMinutes(1);
        assertEquals(1, this.clock.nowDateInMinutes);
    }

    @Test
    public void testSetInutialDateInMinutes() {
        assertNotEquals(0, this.clock.initialDateInMinutes);
        this.clock.setInitialDateInMinutes(1);
        assertEquals(1, this.clock.initialDateInMinutes);
    }

    @Test
    public void testIsTheIntervalElapsedInitialEqualsNow() {
        this.clock.setInitialDateInMinutes(1);
        this.clock.setNowDateInMinutes(1);
        assertFalse(this.clock.isTheIntervalElapsed(1));
    }

    @Test
    public void testIsTheIntervalElapsedInitialNotEqualsNowButNotIntervalElapsed() {
        this.clock.setInitialDateInMinutes(0);
        this.clock.setNowDateInMinutes(1);
        assertFalse(this.clock.isTheIntervalElapsed(2));
    }

    @Test
    public void testIsTheIntervalElapsedInitialNotEqualsNowAndIntervalElapsed() {
        this.clock.setInitialDateInMinutes(0);
        this.clock.setNowDateInMinutes(2);
        assertTrue(this.clock.isTheIntervalElapsed(2));
    }

}
