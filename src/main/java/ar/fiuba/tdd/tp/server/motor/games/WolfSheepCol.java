package ar.fiuba.tdd.tp.server.motor.games;

import ar.fiuba.tdd.tp.server.motor.EntityCompatibilityChecker;
import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.ConditionalCross;
import ar.fiuba.tdd.tp.server.motor.commands.LeaveFromBoat;
import ar.fiuba.tdd.tp.server.motor.commands.LookAround;
import ar.fiuba.tdd.tp.server.motor.commands.TakeToBoat;
import ar.fiuba.tdd.tp.server.motor.entities.Boat;
import ar.fiuba.tdd.tp.server.motor.entities.Col;
import ar.fiuba.tdd.tp.server.motor.entities.Sheep;
import ar.fiuba.tdd.tp.server.motor.entities.Wolf;

public class WolfSheepCol extends Game {
    Stage southShore = new Stage("south-shore");
    Stage northShore = new Stage("north-shore");
    Sheep sheep = new Sheep();
    Wolf wolf = new Wolf();
    Col col = new Col();

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
    }

    @Override
    boolean isGameOver() {
        return (northShore.containsEntity(sheep) && northShore.containsEntity(wolf) && northShore.containsEntity(col));
    }

    @Override
    String getGameOverMessage() {
        return "You win, what a genius!";
    }
}
