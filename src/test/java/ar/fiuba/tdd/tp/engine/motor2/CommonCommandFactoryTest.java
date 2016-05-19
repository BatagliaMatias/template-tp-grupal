package ar.fiuba.tdd.tp.engine.motor2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mbataglia on 18/05/16.
 */
public class CommonCommandFactoryTest {

    @Test
    public void getLook() throws Exception {
        CommonCommandFactory commonCommandFactory = new CommonCommandFactory();
        Container stick = new Container("Stick");
        State stickStates = new State();
        stickStates.setState("visible",true);
        stick.setState(stickStates);
        Command lookAt = commonCommandFactory.getLook("look at");
        lookAt.setComponent(stick);
        assertEquals(lookAt.execute(),"There are Stick ");
    }

    @Test
    public void testHelp() throws Exception {
        CommonCommandFactory commonCommandFactory = new CommonCommandFactory();
        Command help = commonCommandFactory.help("help message");
        assertEquals(help.execute(),"HELP: help message");
    }
}