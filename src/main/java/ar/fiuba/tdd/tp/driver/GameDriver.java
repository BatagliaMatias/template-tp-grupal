package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.engine.motor2.GameState;
import ar.fiuba.tdd.tp.server.PlayerConnection;

/**
 * Created by jorlando on 19/05/16.
 */
public interface GameDriver {

    void initGame(String jarPath) throws GameLoadFailedException;

    String sendCommand(String cmd);

    String sendCommand(String cmd, PlayerConnection player);

    GameState getCurrentState();
}
