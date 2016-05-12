package ar.fiuba.tdd.tp.motor2;

import ar.fiuba.tdd.tp.server.nuevomotor.Game;
import ar.fiuba.tdd.tp.server.nuevomotor.OpenDoor2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testGameOpenDoor2 {

    @Test
    public void abrirSinLlave() {

        Game gameOpenDoor2 = (new OpenDoor2()).build();

        assertEquals(gameOpenDoor2.excute("open door"),"Ey! Where do you go?! Room is locked");

    }

    @Test
    public void abrirCaja() {

        Game gameOpenDoor2 = (new OpenDoor2()).build();

        assertEquals(gameOpenDoor2.excute("open box"),"The box is open");

    }

    @Test
    public void ganarJuego() {

        Game gameOpenDoor2 = (new OpenDoor2()).build();
        gameOpenDoor2.excute("open box");
        gameOpenDoor2.excute("pick key");
        gameOpenDoor2.excute("open door");

        assertTrue(gameOpenDoor2.iWin());

    }

}

