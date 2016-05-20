package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.driver.ConcreteGameDriver;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;
import ar.fiuba.tdd.tp.engine.motor2.GameState;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mbataglia on 20/05/16.
 */
public class EscapeTest {

    @Test
    public void winGame() throws GameLoadFailedException {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("Escape");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon2");
        driver.sendCommand("pick Martillo");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte using Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto in Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial in Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Baranda");
        driver.sendCommand("break Ventana using Martillo");
        driver.sendCommand("goto Afuera");
        assertEquals(GameState.Won,driver.getCurrentState());
    }

    @Test
    public void lostGameMartillo() throws GameLoadFailedException {
        GameDriver  driver = new ConcreteGameDriver();
        driver.initGame("Escape");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte using Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto in Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial in Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Baranda");
        assertEquals(GameState.Lost,driver.getCurrentState());
    }

    @Test
    public void lostGameEscalera() throws GameLoadFailedException {
        GameDriver  driver = new ConcreteGameDriver();
        driver.initGame("Escape");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte using Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto in Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial in Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Escalera");
        assertEquals(GameState.Lost,driver.getCurrentState());
    }

}