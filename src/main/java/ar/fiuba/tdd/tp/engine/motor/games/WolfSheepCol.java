package ar.fiuba.tdd.tp.engine.motor.games;

import ar.fiuba.tdd.tp.engine.motor.*;
import ar.fiuba.tdd.tp.engine.motor.commands.*;
import ar.fiuba.tdd.tp.engine.motor.entities.*;

public class WolfSheepCol extends Game {
    private Stage southShore = new Stage("south-shore");
    private Stage northShore = new Stage("north-shore");
    private Sheep sheep = new Sheep();
    private Wolf wolf = new Wolf();
    private Col col = new Col();

    public WolfSheepCol() {
        southShore.addEntity(wolf);
        southShore.addEntity(sheep);
        southShore.addEntity(col);

        Player player = new Player();
        player.setlocation(southShore);

        EntityCompatibilityChecker checker = new EntityCompatibilityChecker();
        checker.addIncompatiblePair(wolf, sheep);
        checker.addIncompatiblePair(col, sheep);

        Boat boat = new Boat(1, player);
        commands.add(new LookAround(player));
        commands.add(new TakeToBoat(sheep, boat));
        commands.add(new TakeToBoat(col, boat));
        commands.add(new TakeToBoat(wolf, boat));
        commands.add(new LeaveFromBoat(sheep, boat));
        commands.add(new LeaveFromBoat(col, boat));
        commands.add(new LeaveFromBoat(wolf, boat));
        commands.add(new ConditionalCross(southShore, player, checker));
        commands.add(new ConditionalCross(northShore, player, checker));
        includeWhatCanIdoWithCommand();
    }

    @Override
    boolean isGameOver() {
        return (northShore.containsEntity(sheep) && northShore.containsEntity(wolf) && northShore.containsEntity(col));
    }

    @Override
    String getGameOverMessage() {
        return "You won, what a genius!";
    }
}
