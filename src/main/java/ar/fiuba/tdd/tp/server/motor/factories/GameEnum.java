package ar.fiuba.tdd.tp.server.motor.factories;

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



    public static FactoryGames getGame(String nameToAnalize) {
        try {
            return GameEnum.valueOf(nameToAnalize.toUpperCase()).getFactory();
        } catch (IllegalArgumentException t) {
            //ACA se podria lanzar una excepcion custom y donde se use este metodo se catchea
            // y se muestra un mensaje lindo como que el juego no existe
        }
        return null;
    }

}
