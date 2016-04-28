package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.commands.CheckTop;
import ar.fiuba.tdd.tp.server.motor.commands.MoveDisk;
import ar.fiuba.tdd.tp.server.motor.entities.StackHanoi;

public class TowerOfHanoi extends Game {

    private static String helpMessage = "The objective of the puzzle is to move the entire stack to another rod.";
    private StackHanoi stackHanoiFirst;
    private StackHanoi stackHanoiSecond;
    private StackHanoi stackHanoiThird;

    public TowerOfHanoi() {

        this.stackHanoiFirst = new StackHanoi("1");
        this.stackHanoiSecond = new StackHanoi("2");
        this.stackHanoiThird = new StackHanoi("3");

        this.stackHanoiFirst.putDisks();

        this.commands.add(new CheckTop(stackHanoiFirst));
        this.commands.add(new CheckTop(stackHanoiSecond));
        this.commands.add(new CheckTop(stackHanoiThird));

        this.commands.add(new MoveDisk(stackHanoiFirst, stackHanoiSecond));
        this.commands.add(new MoveDisk(stackHanoiFirst, stackHanoiThird));
        this.commands.add(new MoveDisk(stackHanoiSecond, stackHanoiFirst));
        this.commands.add(new MoveDisk(stackHanoiSecond, stackHanoiThird));
        this.commands.add(new MoveDisk(stackHanoiThird, stackHanoiFirst));
        this.commands.add(new MoveDisk(stackHanoiThird, stackHanoiSecond));
        includeWhatCanIdoWithCommand();
    }

    public static String getHelp() {

        return helpMessage;
    }

    @Override
    public boolean isGameOver() {

        boolean stackFirsEmpty = (this.stackHanoiFirst.checkSizeTop() == 0);
        boolean stackSecondComplete = this.stackHanoiSecond.isComplete();
        boolean stackThirdComplete = this.stackHanoiThird.isComplete();

        return stackFirsEmpty & (stackSecondComplete | stackThirdComplete);

    }

    @Override
    public String getGameOverMessage() {
        return "You won the game tower of Hanoi !";
    }
}
