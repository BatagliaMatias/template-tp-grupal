package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.engine.motor2.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Escape2 implements GameBuilder {
    @SuppressWarnings("CPD-START")

    @Override
    public Game build() {
        Game gameEscape2 = new Game();
        CommonCommandFactory commonCommandFactory = new CommonCommandFactory();
        Command help = commonCommandFactory.help("help", "Tenes que escapar por el sotano de la biblioteca");

        Container foto = new Container("foto");

        //lugares
        Container pasillo = new Container("pasillo");
        gameEscape2.setInitialPosition(pasillo);
        pasillo.setComponent(foto);

        Container afuera = new Container("afuera");
        //salon1
        Container salon1 = new Container("salon1");
        Container cuadroBarco = new Container("cuadroBarco");
        Container cajaFuerte = new Container("cajaFuerte");
        cuadroBarco.setComponent(cajaFuerte);

        Container botellaLicor = new Container("botellaLicor");
        Container credencial = new Container("credencial");
        salon1.setComponent(credencial);
        salon1.setComponent(cuadroBarco);
        salon1.setComponent(botellaLicor);


        //salon2
        Container salon2 = new Container("salon2");
        Container martillo = new Container("martillo");
        salon2.setComponent(martillo);

        //salon3
        Container salon3 = new Container("salon3");
        Container llave = new Container("llave");
        salon3.setComponent(llave);

        //BiblotecaAcceso
        Container bibliotecaAcceso = new Container("bibliotecaAcceso");
        Container bibliotecario = new Container("bibliotecario");
        bibliotecaAcceso.setComponent(bibliotecario);

        //Bibloteca
        Container biblioteca = new Container("biblioteca");
        Container libroViejo = new Container("libroViejo");
        biblioteca.setComponent(libroViejo);

        //Sotano
        Container sotano = new Container("sotano");
        Container escalera = new Container("escalera");
        Container baranda = new Container("baranda");
        sotano.setComponent(escalera);
        sotano.setComponent(baranda);

        //SotanoAbajo
        Container sotanoAbajo = new Container("sotanoAbajo");
        Container ventana = new Container("ventana");
        Container ventanaRota = new Container("ventanaRota");
        sotanoAbajo.setComponent(ventana);


        PlayerCommand pickFoto = new PlayerCommand("pick foto");
        pickFoto.setPlayerCommand((Container player) -> {
            if (player.getParent() == pasillo) {
                player.setComponent(foto);
                return "agarraste una foto";
            }
            return "Ya tenes una foto";
        });

        PlayerCommand gotoBibliotecaAcceso = new PlayerCommand("goto BibliotecaAcceso");
        gotoBibliotecaAcceso.setPlayerCommand((Container player) -> {
            if (player.getParent() == pasillo) {
                pasillo.removeComponent(player);
                bibliotecaAcceso.setComponent(player);
                return "Entraste al acceso de la bibloteca";
            }
            return "No puedo moverme";
        });

        PlayerCommand gotoSalon1 = new PlayerCommand("goto Salon1");
        gotoSalon1.setPlayerCommand((Container player) -> {
            if (player.getParent() == pasillo) {
                pasillo.removeComponent(player);
                salon1.setComponent(player);
                return "Entraste al Salon1";
            }
            return "No puedo moverme";
        });

        PlayerCommand gotoSalon2 = new PlayerCommand("goto Salon2");
        gotoSalon1.setPlayerCommand((Container player) -> {
            if (player.getParent() == pasillo) {
                pasillo.removeComponent(player);
                salon2.setComponent(player);
                return "Entraste al Salon2";
            }
            return "No puedo moverme";
        });

        PlayerCommand gotoSalon3 = new PlayerCommand("goto Salon3");
        gotoSalon1.setPlayerCommand((Container player) -> {
            if (player.getParent() == pasillo) {
                pasillo.removeComponent(player);
                salon3.setComponent(player);
                return "Entraste al Salon3";
            }
            return "No puedo moverme";
        });

        PlayerCommand gotoPasillo = new PlayerCommand("goto Pasillo");
        gotoPasillo.setPlayerCommand((Container player) -> {
            ArrayList<Container> origenes = new ArrayList();
            origenes.add(salon1);
            origenes.add(salon2);
            origenes.add(salon3);
            origenes.add(bibliotecaAcceso);
            for (Container origen : origenes) {
                if (player.getParent() == origen) {
                    origen.removeComponent(player);
                    pasillo.setComponent(player);
                    return "Entraste al Pasillo";
                }
            }
            return "No puedo moverme";
        });

        PlayerCommand pickLLave = new PlayerCommand("pick Llave");
        pickLLave.setPlayerCommand((Container player) -> {
            if (player.getParent() == salon3 && salon3.contains(llave)) {
                salon3.removeComponent(llave);
                player.setComponent(llave);
                return "Tenes la llave";
            }
            return "Que llave?";
        });

        PlayerCommand pickMartillo = new PlayerCommand("pick Martillo");
        pickMartillo.setPlayerCommand((Container player) -> {
            if (player.getParent() == salon2 && salon2.contains(martillo)) {
                salon2.removeComponent(martillo);
                player.setComponent(martillo);
                return "Tenes el Martillo";
            }
            return "Que Martillo?";
        });

        PlayerCommand moveCuadroBarco = new PlayerCommand("move CuadroBarco");
        moveCuadroBarco.setPlayerCommand((Container player) -> {
            if (player.getParent() == salon1 && salon1.contains(cuadroBarco)) {
                salon1.removeComponent(cuadroBarco);
                return "Moviste el cuadro";
            }
            return "Que Cuadro con un Barco?";
        });

        PlayerCommand openCajaFuerte = new PlayerCommand("open CajaFuerte using Llave");
        openCajaFuerte.setPlayerCommand((Container player) -> {
            if (player.getParent() == salon1 && !salon1.contains(cuadroBarco) && player.contains(llave)) {
                salon1.removeComponent(cajaFuerte);
                return "Abriste la cajaFuerte!";
            }
            return "No podes abrirla";
        });

        PlayerCommand pickCredencial = new PlayerCommand("pick Credencial");
        openCajaFuerte.setPlayerCommand((Container player) -> {
            if (player.getParent() == salon1 && !salon1.contains(cajaFuerte) && salon1.contains(credencial)) {
                salon1.removeComponent(credencial);
                player.setComponent(credencial);
                return "Tenes la credencial";
            }
            return "No puedo agarrar la credencial";
        });

        PlayerCommand putFotoInCredencial = new PlayerCommand("put Foto in Credencial");
        putFotoInCredencial.setPlayerCommand((Container player) -> {
            if (player.contains(credencial) && player.contains(foto)) {
                credencial.setComponent(foto);
                player.removeComponent(foto);
                return "Tenes la credencial con la foto";
            }
            return "No puedo";
        });
        


        gameEscape2.setPlayerCommand(pickFoto);
        gameEscape2.setPlayerCommand(gotoBibliotecaAcceso);
        gameEscape2.setPlayerCommand(gotoSalon1);
        gameEscape2.setPlayerCommand(gotoSalon2);
        gameEscape2.setPlayerCommand(gotoSalon3);
        gameEscape2.setPlayerCommand(gotoPasillo);
        gameEscape2.setPlayerCommand(pickLLave);
        gameEscape2.setPlayerCommand(pickMartillo);
        gameEscape2.setPlayerCommand(moveCuadroBarco);
        gameEscape2.setPlayerCommand(openCajaFuerte);
        gameEscape2.setPlayerCommand(pickCredencial);
        gameEscape2.setPlayerCommand(putFotoInCredencial);
        gameEscape2.setExecutableCommands(help);
        return gameEscape2;

    }
}
