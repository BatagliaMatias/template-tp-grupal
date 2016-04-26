package ar.fiuba.tdd.tp;

import java.util.ArrayList;
import java.util.List;

public class AbrirPuerta2 extends Game {

    public AbrirPuerta2() {

        List<GameEntity> worldEntities = new ArrayList<GameEntity>();
        List<GameEntity> entitiesInsideBox = new ArrayList<GameEntity>();

        Door puerta = new Door();
        Key llave = new Key(puerta);
        entitiesInsideBox.add(llave);
        Box box = new Box(entitiesInsideBox,worldEntities);

        worldEntities.add(puerta);
        worldEntities.add(box);

        commands.add(new LookAround(worldEntities));
        commands.add(new Open(puerta));
        commands.add(new Open(box));
        commands.add(new Close(puerta));
        commands.add(new Close(box));
        commands.add(new Pick(llave));
    }

}
