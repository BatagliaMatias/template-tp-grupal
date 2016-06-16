package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;
import ar.fiuba.tdd.tp.engine.motor2.Container;
import ar.fiuba.tdd.tp.engine.motor2.Game;
import ar.fiuba.tdd.tp.engine.motor2.GameState;
import ar.fiuba.tdd.tp.server.PlayerConnection;
import ar.fiuba.tdd.tp.server.PlayerIDProvider;
import org.junit.Assert;
import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.*;

/**
 * Created by jorlando on 16/06/16.
 */
public class Escape2Test {

    private PlayerIDProvider idProvider = new PlayerIDProvider();
    private PlayerIDProvider secondIdProvider= new PlayerIDProvider();
    String message = "";

    @Test
    public void lostGame() throws GameLoadFailedException {
        ConcreteGameDriver driver = new ConcreteGameDriver();
        driver.initGame("Escape2");
        Game game = driver.getGame();
        PlayerConnection player1 = new PlayerConnection(new Socket(), idProvider, game, "hello");
        PlayerConnection player2 = new PlayerConnection(new Socket(), idProvider, game, "hello");
        game.addPlayer(player1);
        game.addPlayer(player2);

        driver.sendCommand("goto Salon1", player1);
        driver.sendCommand("pick BotellaLicor", player1);
        driver.sendCommand("goto Pasillo", player1);
        driver.sendCommand("goto BibliotecaAcceso", player1);
        Container bibliotecario = game.getContainer("bibliotecario");
        Container energia = game.getContainer("energia");
        //Antes de darle el licor, el bibliotecario tiene energia
        assertTrue(bibliotecario.contains(energia));

        message = driver.sendCommand("give BotellaLicor in Bibliotecario", player1);
        assertEquals("Se tomo todo el Licor", message);
        //El bibliotecario ya no tiene energia y se quedo dormido
        assertFalse(bibliotecario.contains(energia));

        //******************************************************************************************
        // Simulo el paso del tiempo para que el bibliotecario se despierte
        //******************************************************************************************
        //1: seteo que la hora de inicio fue hace 5 minutos
        game.scheduler.clock.setInitialDateInMinutes((game.scheduler.clock.actualTimeInMinutes() - 2));
        //2: seteo que la ultima vez que corrio el job fue hace 2 minutos
        game.scheduler.setLastRun(game.scheduler.clock.actualTimeInMinutes() - 2);
        // 3: le doy 500 ms para asegurarme que el ciclo del job corre
        this.sleep();
        //4: como ya corrio el job, el bibliotecario tiene energia
        assertTrue(bibliotecario.contains(energia));
        driver.sendCommand("goto Biblioteca", player1);
        assertEquals(GameState.Lost, player1.getGameState());
    }

    @Test
    public void onePlayerGetsKilledByLibrerian() throws GameLoadFailedException {
        ConcreteGameDriver driver = new ConcreteGameDriver();
        driver.initGame("Escape2");
        Game game = driver.getGame();
        PlayerConnection player1 = new PlayerConnection(new Socket(), secondIdProvider , game, "hello");
        PlayerConnection player2 = new PlayerConnection(new Socket(), secondIdProvider , game, "hello");
        game.addPlayer(player1);
        game.addPlayer(player2);

        driver.sendCommand("goto Salon1", player1);
        driver.sendCommand("pick BotellaLicor", player1);
        driver.sendCommand("goto Pasillo", player1);
        driver.sendCommand("goto BibliotecaAcceso", player1);

        Container bibliotecario = game.getContainer("bibliotecario");
        Container energia = game.getContainer("energia");
        //Antes de darle el licor, el bibliotecario tiene energia
        assertTrue(bibliotecario.contains(energia));

        String message = "";
        message = driver.sendCommand("give BotellaLicor in Bibliotecario", player1);
        assertEquals("Se tomo todo el Licor", message);
        //El bibliotecario ya no tiene energia y se quedo dormido
        assertFalse(bibliotecario.contains(energia));

        driver.sendCommand("goto Salon1", player2);
        driver.sendCommand("goto Pasillo", player2);
        driver.sendCommand("goto BibliotecaAcceso", player2);


        //******************************************************************************************
        // Simulo el paso del tiempo para que el bibliotecario se despierte
        //******************************************************************************************
        //1: seteo que la hora de inicio fue hace 5 minutos
        game.scheduler.clock.setInitialDateInMinutes((game.scheduler.clock.actualTimeInMinutes() - 2));
        //2: seteo que la ultima vez que corrio el job fue hace 2 minutos
        game.scheduler.setLastRun(game.scheduler.clock.actualTimeInMinutes() - 2);
        // 3: le doy 500 ms para asegurarme que el ciclo del job corre
        this.sleep();
        //4: como ya corrio el job, el bibliotecario tiene energia
        assertTrue(bibliotecario.contains(energia));

        driver.sendCommand("goto Biblioteca", player1);
        driver.sendCommand("goto Pasillo", player2);

        assertEquals(GameState.Lost, player1.getGameState());
        assertNotEquals(GameState.Lost, player2.getGameState());
    }


    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
