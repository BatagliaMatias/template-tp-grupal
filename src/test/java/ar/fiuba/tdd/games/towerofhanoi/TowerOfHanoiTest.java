package ar.fiuba.tdd.games.towerofhanoi;

import ar.fiuba.tdd.tp.server.motor.commands.CheckTop;
import ar.fiuba.tdd.tp.server.motor.commands.MoveDisk;
import ar.fiuba.tdd.tp.server.motor.entities.StackHanoi;
import ar.fiuba.tdd.tp.server.motor.games.TowerOfHanoi;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TowerOfHanoiTest {

    @Test
    public void ifMoveTopStack1Stack2TheSizeTopDiskInTopStack1Incremented() {

        StackHanoi stackFirst = new StackHanoi("1");
        StackHanoi stackSecond = new StackHanoi("2");

        stackFirst.putDisks();

        assertEquals(stackFirst.checkSizeTop(), 1);

        stackSecond.setDisk(stackFirst.getTop());

        assertEquals(stackFirst.checkSizeTop(), 2);

    }

    @Test
    public void commandMoveTheDiscStack1ToStack2() {

        StackHanoi stackFirst = new StackHanoi("1");
        StackHanoi stackSecond = new StackHanoi("2");

        stackFirst.putDisks();

        MoveDisk command = new MoveDisk(stackFirst, stackSecond);

        assertEquals(command.execute(), "moved!");

    }


    @Test
    public void commandCheckTopEmpty() {

        StackHanoi stackFirst = new StackHanoi("1");

        CheckTop command = new CheckTop(stackFirst);

        assertEquals(command.execute(), "The stack 1 is empty");

    }

    @Test
    public void commandCheckTop() {

        StackHanoi stackFirst = new StackHanoi("1");

        stackFirst.putDisks();

        CheckTop command = new CheckTop(stackFirst);

        assertEquals(command.execute(), "Size of top from stack 1 is 1");

    }

    @Test
    public void moveADiskToAStackWithSmallerDiscsShouldNotBeAllowed() {

        StackHanoi stackFirst = new StackHanoi("1");
        StackHanoi stackSecond = new StackHanoi("2");

        stackFirst.putDisks();

        MoveDisk command = new MoveDisk(stackFirst, stackSecond);

        command.execute();

        assertEquals(command.execute(), "The disk in stack 1 is greater than disk in stack 2");

    }

    @Test
    public void gameTowerOfHanoiCheckStack1() {

        TowerOfHanoi game = new TowerOfHanoi();

        String response = game.processInput("check top stack 1");

        assertEquals(response, "Size of top from stack 1 is 1");

    }

    @Test
    public void gameTowerOfHanoiMoveTopStack1ToStack2() {

        TowerOfHanoi game = new TowerOfHanoi();

        String response = game.processInput("move top stack 1 stack 2");

        assertEquals(response, "moved!");

    }


}
