package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.engine.motor2.GameState;

/**
 * Created by jorlando on 19/05/16.
 */
public interface GameDriver {

    void initGame(String jarPath) throws GameLoadFailedException;

    String sendCommand(String cmd);

    GameState getCurrentState();
}
