package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.engine.motor2.Command;
import ar.fiuba.tdd.tp.engine.motor2.Container;
import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameBuilder;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class WolfSheepCol implements GameBuilder {

    @Override
    public Game build() {

        Container southShore = new Container("south shore");
        Container northShore = new Container("north shore");
        Container wolf = new Container("wolf");
        Container sheep = new Container("sheep");
        Container col = new Container("col");
        Container boat = new Container("boat");

        southShore.setComponent(boat);
        southShore.setComponent(wolf);
        southShore.setComponent(sheep);
        southShore.setComponent(col);

        Command takeSheep = new Command("take sheep");
        takeSheep.setExecutableCommand((HashMap<String, Container> components) -> {
            Container target = sheep;
            if (boat.getSize() > 0) {
                return "tried to take the " + target.getName() + " but the boat is full";
            }
            if (target.getParent() != boat.getParent()) {
                return "tried to take the " + target.getName() + " but the boat is on the other shore";
            }
            target.getParent().removeComponent(target);
            boat.setComponent(target);
            return "placed the " + target.getName() + " in the boat";
        });

        Command leaveSheep = new Command("leave sheep");
        leaveSheep.setExecutableCommand((HashMap<String, Container> components) -> {
            Container target = sheep;
            if (!boat.contains(target)) {
                return "tried to leave the " + target.getName() + " but it is not in the boat";
            }
            boat.removeComponent(target);
            boat.getParent().setComponent(target);
            return "placed the " + target.getName() + " in " + target.getParent().getName();
        });

        Command takeWolf = new Command("take wolf");
        takeWolf.setExecutableCommand((HashMap<String, Container> components) -> {
            Container target = wolf;
            if (boat.getSize() > 0) {
                return "tried to take the " + target.getName() + " but the boat is full";
            }
            if (target.getParent() != boat.getParent()) {
                return "tried to take the " + target.getName() + " but the boat is on the other shore";
            }
            target.getParent().removeComponent(target);
            boat.setComponent(target);
            return "placed the " + target.getName() + " in the boat";
        });

        Command leaveWolf = new Command("leave wolf");
        leaveWolf.setExecutableCommand((HashMap<String, Container> components) -> {
            Container target = wolf;
            if (!boat.contains(target)) {
                return "tried to leave the " + target.getName() + " but it is not in the boat";
            }
            boat.removeComponent(target);
            boat.getParent().setComponent(target);
            return "placed the " + target.getName() + " in " + target.getParent().getName();
        });

        Command takeCol = new Command("take col");
        takeCol.setExecutableCommand((HashMap<String, Container> components) -> {
            Container target = col;
            if (boat.getSize() > 0) {
                return "tried to take the " + target.getName() + " but the boat is full";
            }
            if (target.getParent() != boat.getParent()) {
                return "tried to take the " + target.getName() + " but the boat is on the other shore";
            }
            target.getParent().removeComponent(target);
            boat.setComponent(target);
            return "placed the " + target.getName() + " in the boat";
        });

        Command leaveCol = new Command("leave col");
        leaveCol.setExecutableCommand((HashMap<String, Container> components) -> {
            Container target = col;
            if (!boat.contains(col)) {
                return "tried to leave the " + target.getName() + " but it is not in the boat";
            }
            boat.removeComponent(target);
            boat.getParent().setComponent(target);
            return "placed the " + target.getName() + " in " + target.getParent().getName();
        });

        Command crossNorth = new Command("cross north-shore");
        crossNorth.setExecutableCommand((HashMap<String, Container> components) -> {
            Container origin = southShore;
            if (origin != boat.getParent()) {
                return "tried to cross from " + origin.getName() + " but the boat is not there";
            }
            if ((wolf.getParent() == sheep.getParent() || sheep.getParent() == col.getParent())) {
                return "tried to cross from " + origin.getName() + " but he cant leave those two together";
            }
            southShore.removeComponent(boat);
            northShore.setComponent(boat);
            return "crossed the boat of shore!";
        });

        Command crossSouth = new Command("cross south-shore");
        crossSouth.setExecutableCommand((HashMap<String, Container> components) -> {
            Container origin = northShore;
            if (origin != boat.getParent()) {
                return "tried to cross from " + origin.getName() + " but the boat is not there";
            }
            if ((wolf.getParent() == sheep.getParent() || sheep.getParent() == col.getParent())) {
                return "tried to cross from " + origin.getName() + " but he cant leave those two together";
            }

            northShore.removeComponent(boat);
            southShore.setComponent(boat);
            return "crossed the boat of shore!";
        });


        Game wolfSheepCol = new Game();
        wolfSheepCol.addTimedEvent(true, 50000, () -> "AUTO MSG: the wolf is in " + wolf.getParent().getName() + " the sheep is in " +
                sheep.getParent().getName() + " the col is in " + col.getParent().getName());

        wolfSheepCol.setExecutableCommands(takeCol);
        wolfSheepCol.setExecutableCommands(leaveCol);
        wolfSheepCol.setExecutableCommands(takeSheep);
        wolfSheepCol.setExecutableCommands(leaveSheep);
        wolfSheepCol.setExecutableCommands(takeWolf);
        wolfSheepCol.setExecutableCommands(leaveWolf);

        wolfSheepCol.setExecutableCommands(crossNorth);
        wolfSheepCol.setExecutableCommands(crossSouth);

        wolfSheepCol.setWinCondition(() ->
                (northShore.contains(wolf) && northShore.contains(sheep) && northShore.contains(col)));

        return wolfSheepCol;
        //@SuppressWarnings("CPD-END")
    }
}
