package ar.fiuba.tdd.tp;

import java.util.ArrayList;
import java.util.List;

public class AbrirPuerta2 extends Game {

    public AbrirPuerta2() {

        List<GameEntity> entities = new ArrayList<GameEntity>();
        entities.add(new Door());

        commands = new ArrayList<GameCommand>();
        commands.add(new LookAround(entities));
    }

}
