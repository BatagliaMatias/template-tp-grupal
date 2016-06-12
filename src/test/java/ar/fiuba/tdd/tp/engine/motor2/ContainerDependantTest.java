package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jorlando on 18/05/16.
 */
public class ContainerDependantTest {

    Container dependency = null;
    String stateTest = "visible";

    @Before
    public void setUp() {
        State keyStates = new State();
        keyStates.setState("visible",true);
        keyStates.setState("false",false);
        dependency = new Container("Key");
        dependency.setState(keyStates);
    }

    @Test
    public void testGetUnsuccessfulMessage() {
        String messageUnsuccessful = "which key?";
        ContainerDependant dependant = new ContainerDependant(dependency, stateTest, messageUnsuccessful);
        assertEquals(dependant.getUnsuccessfulMessage(), messageUnsuccessful);
    }

    @Test
    public void testIsDependantAvailableTrue() {
        ContainerDependant dependant = new ContainerDependant(dependency, stateTest, "which key?");
        assertTrue(dependant.isDependantAvailable());
    }

    @Test
    public void testIsDependantAvailableFalse() {
        ContainerDependant dependant = new ContainerDependant(dependency, "false", "which key?");
        assertFalse(dependant.isDependantAvailable());
    }

    @Test
    public void testIsDependantAvailableFalseWithInvalidState() {
        ContainerDependant dependant = new ContainerDependant(dependency, "invalid", "which key?");
        assertFalse(dependant.isDependantAvailable());
    }
}
