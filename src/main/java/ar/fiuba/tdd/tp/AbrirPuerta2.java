package ar.fiuba.tdd.tp;

import java.util.ArrayList;
import java.util.List;

public class AbrirPuerta2 extends Game {

    private Stage finalRoom;
    private Player player;

    public AbrirPuerta2() {

        Stage room1 = new Stage("Room 1");
        finalRoom = new Stage("Room 2");

        player = new Player();
        player.setlocation(room1);

        // estos worldEntites deberian ser StageEntities (tratar de hacerlo como los container de Matias), entonces varian dependendiendo de que stage estes, para este juego no es necesario asi que por ahora no lo hago
        List<GameEntity> worldEntities = new ArrayList<GameEntity>();
        List<GameEntity> entitiesInsideBox = new ArrayList<GameEntity>();
        List<GameEntity> playerInventory = player.getInventory();

        Door puerta = new Door(player, finalRoom);
        Key llave = new Key(puerta, worldEntities, playerInventory);

        entitiesInsideBox.add(llave);
        Box box = new Box(entitiesInsideBox, worldEntities);

        worldEntities.add(puerta);
        worldEntities.add(box);

        commands.add(new LookAround(worldEntities));
        commands.add(new Open(puerta));
        commands.add(new Open(box));
        commands.add(new Close(box));
        commands.add(new Pick(llave, worldEntities));
    }

    @Override
    boolean isGameOver() {
        return player.getLocation() == finalRoom;
    }

    @Override
    String getGameOverMessage() {
        return "GANASTE!. A esto le falta variar el mensaje si perdes";
    }
}
