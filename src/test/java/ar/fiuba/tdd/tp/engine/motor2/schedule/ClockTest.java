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
        long dateInMinute = (long)Math.floor((((new Date()).getTime()) / (60000)))*1000;
        assertEquals(dateInMinute, this.clock.actualTimeInMinutes());
    }

    @Test
    public void testReload() {
        assertEquals(0, this.clock.nowDateInMinutes);
        this.clock.reload();
        assertNotEquals(0, this.clock.nowDateInMinutes);
    }

    @Test
    public void testSetNowDateInMinutes() {
        assertEquals(0, this.clock.nowDateInMinutes);
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
        this.clock.setNowDateInMinutes(1000);
        assertFalse(this.clock.isTheIntervalElapsed(2000));
    }

    @Test
    public void testIsTheIntervalElapsedInitialNotEqualsNowAndIntervalElapsed() {
        this.clock.setInitialDateInMinutes(0);
        this.clock.setNowDateInMinutes(2000);
        assertTrue(this.clock.isTheIntervalElapsed(2000));
    }

}
