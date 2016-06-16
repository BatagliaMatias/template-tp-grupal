package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.engine.motor2.Container;
import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameBuilder;
import ar.fiuba.tdd.tp.engine.motor2.PlayerCommand;

public class TowersOfHanoi implements GameBuilder{
    @SuppressWarnings("CPD-START")
    int diskQuantity = 4;
    @Override
    public Game build() {
        Game towersOfHanoi = new Game();
        Container towerA = new Container("towerA");
        Container towerB = new Container("towerB");
        Container towerC = new Container("towerC");
        Container disk1 = new Container("1");
        Container disk2 = new Container("2");
        Container disk3 = new Container("3");
        Container disk4 = new Container("4");
        //towersOfHanoi.setInitialPosition(towerA);
        towerA.setComponent(disk1);
        towerA.setComponent(disk2);
        towerA.setComponent(disk3);
        towerA.setComponent(disk4);

        PlayerCommand moveAToB = new PlayerCommand("A to B");
        moveAToB.setPlayerCommand((Container player) -> {
                int diskA = calculateDisk(towerA);
                int diskB = calculateDisk(towerB);

                if (diskA > 0 && (diskB > diskA || diskB == 0)) {
                    Container disk = removeDisk(towerA);
                    towerB.setComponent(disk);
                    return "disk " + disk.getName() + " from A to B";
                }
                return "you cant move that A:" + diskA + " B: " + diskB;
            });

        PlayerCommand moveBToA = new PlayerCommand("B to A");
        moveBToA.setPlayerCommand((Container player) -> {
                int diskA = calculateDisk(towerA);
                int diskB = calculateDisk(towerB);

                if (diskB > 0 && (diskA > diskB || diskA == 0)) {
                    Container disk = removeDisk(towerB);
                    towerA.setComponent(disk);
                    return "disk " + disk.getName() + " from B to A";
                }
                return "you cant move that";
            });

        PlayerCommand moveAToC = new PlayerCommand("A to C");
        moveAToC.setPlayerCommand((Container player) -> {
                int diskA = calculateDisk(towerA);
                int diskC = calculateDisk(towerC);

                if (diskA > 0 && (diskC > diskA || diskC == 0)) {
                    Container disk = removeDisk(towerA);
                    towerC.setComponent(disk);
                    return "disk " + disk.getName() + " from A to C";
                }
                return "you cant move that";
            });

        PlayerCommand moveBToC = new PlayerCommand("B to C");
        moveBToC.setPlayerCommand((Container player) -> {
                int diskC = calculateDisk(towerC);
                int diskB = calculateDisk(towerB);

                if (diskB > 0 && (diskC > diskB || diskC == 0)) {
                    Container disk = removeDisk(towerB);
                    towerC.setComponent(disk);
                    return "disk " + disk.getName() + " from B to C";
                }
                return "you cant move that";
            });

        PlayerCommand moveCToA = new PlayerCommand("C to A");
        moveCToA.setPlayerCommand((Container player) -> {
                int diskA = calculateDisk(towerA);
                int diskC = calculateDisk(towerC);

                if (diskC > 0 && (diskA > diskC || diskA == 0)) {
                    Container disk = removeDisk(towerC);
                    towerA.setComponent(disk);
                    return "disk  " + disk.getName() + " from C to A";
                }
                return "you cant move that";
            });

        PlayerCommand moveCToB = new PlayerCommand("C to B");
        moveCToB.setPlayerCommand((Container player) -> {
                int diskC = calculateDisk(towerC);
                int diskB = calculateDisk(towerB);

                if (diskC > 0 && (diskB > diskC || diskB == 0)) {
                    Container disk = removeDisk(towerC);
                    towerB.setComponent(disk);
                    return "disk " + disk.getName() + " from C to B";
                }
                return "you cant move that";
            });

        PlayerCommand checkTopA = new PlayerCommand("check top A");
        checkTopA.setPlayerCommand((Container player) -> getTop(towerA));
        PlayerCommand checkTopB = new PlayerCommand("check top B");
        checkTopB.setPlayerCommand((Container player) -> getTop(towerB));
        PlayerCommand checkTopC = new PlayerCommand("check top C");
        checkTopC.setPlayerCommand((Container player) -> getTop(towerC));

        towersOfHanoi.setPlayerCommand(moveAToB);
        towersOfHanoi.setPlayerCommand(moveAToC);
        towersOfHanoi.setPlayerCommand(moveBToA);
        towersOfHanoi.setPlayerCommand(moveBToC);
        towersOfHanoi.setPlayerCommand(moveCToA);
        towersOfHanoi.setPlayerCommand(moveCToB);
        towersOfHanoi.setPlayerCommand(checkTopA);
        towersOfHanoi.setPlayerCommand(checkTopB);
        towersOfHanoi.setPlayerCommand(checkTopC);
        towersOfHanoi.setMaxPlayers(1);
        towersOfHanoi.setWinCondition((Container player) -> (containsAllDisks(towerB) || containsAllDisks(towerC)));
        return towersOfHanoi;
    }


    private int calculateDisk(Container tower) {
        if (tower.getComponents().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(tower.getComponents().get(0).getName());
    }

    private String getTop(Container tower) {
        return tower.getComponents().isEmpty() ? "The tower is empty" : tower.getComponents().get(0).getName();
    }

    private Container removeDisk(Container tower) {
        Container disk = tower.getComponents().get(0);
        tower.removeComponent(disk);
        return disk;
    }

    private boolean containsAllDisks(Container tower) {
        return (tower.getComponents().size() == diskQuantity);
    }

}
