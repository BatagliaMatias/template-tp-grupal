package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.engine.motor2.*;

/**
 * Created by jorlando on 19/05/16.
 */
public class ConcreteGameDriver implements GameDriver {

    Game game = null;
    private String packageOfGames = "ar.fiuba.tdd.tp.engine.motor2.";

    public void initGame(String gameName) {
        try {
            Class<?> gameClass = Class.forName(packageOfGames.concat(gameName));
            GameBuilder builder = (GameBuilder)gameClass.newInstance();
            game = builder.build();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.print("No se pudo instanciar el juego: ".concat(gameName));
        }
    }

    public String sendCommand(String cmd) {
        return game.execute(cmd);
    }

    public GameState getGameState() {
        return game.getState();
    }
}
