package ar.fiuba.tdd.tp.server.motor.factories;

import ar.fiuba.tdd.tp.server.exceptions.BadGameNameException;

public enum GameEnum {

    TOWEROFHANOI(new FactoryTowerOfHanoi()),
    CURSEDOBJECT(new FactoryCursedObject()),
    PICKSTICK(new FactoryPickStick()),
    OPENDOOR2(new FactoryOpenDoor2());

    private FactoryGames factory;

    GameEnum(FactoryGames newFactory) {
        this.factory = newFactory;
    }

    public FactoryGames getFactory() {
        return this.factory;
    }

    public static FactoryGames getGame(String nameToAnalize) throws BadGameNameException {
        try {
            return GameEnum.valueOf(nameToAnalize.toUpperCase()).getFactory();
        } catch (IllegalArgumentException t) {
            throw new BadGameNameException("Game: ".concat(nameToAnalize).concat(" not found."));
        }
    }

}
