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
        Container energia = new Container("energia");
        Container bibliotecaAcceso = new Container("bibliotecaAcceso");
        Container bibliotecario = new Container("bibliotecario");
        bibliotecario.setComponent(energia);
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


        PlayerCommand pickFoto = new PlayerCommand("pick Foto");
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
        gotoSalon2.setPlayerCommand((Container player) -> {
            if (player.getParent() == pasillo) {
                pasillo.removeComponent(player);
                salon2.setComponent(player);
                return "Entraste al Salon2";
            }
            return "No puedo moverme";
        });

        PlayerCommand gotoSalon3 = new PlayerCommand("goto Salon3");
        gotoSalon3.setPlayerCommand((Container player) -> {
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

        PlayerCommand pickBotellaLicor = new PlayerCommand("pick BotellaLicor");
        pickBotellaLicor.setPlayerCommand((Container player) -> {
            if (player.getParent() == salon1 && salon1.contains(botellaLicor)) {
                salon1.removeComponent(botellaLicor);
                player.setComponent(botellaLicor);
                return "Tenes el Licor";
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
        pickCredencial.setPlayerCommand((Container player) -> {
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

        PlayerCommand showCredencial = new PlayerCommand("show Credencial in Bibliotecario");
        showCredencial.setPlayerCommand((Container player) -> {
            if(player.getParent().contains(bibliotecario)) {
                if (player.contains(credencial) && credencial.contains(foto)) {
                    bibliotecario.setComponent(credencial);
                    return "Linda credencial";
                } else {
                    return "Tu credencial no tiene foto";
                }
            }
            return "No estoy en la misma habitacion que el biblotecario";
        });

        PlayerCommand gotoBiblioteca = new PlayerCommand("goto Biblioteca");
        gotoBiblioteca.setPlayerCommand((Container player) -> {
            if (player.getParent() == bibliotecaAcceso && (player.contains(credencial)
                    || !bibliotecaAcceso.contains(bibliotecario)
                    || !bibliotecario.contains(energia))) {
                biblioteca.setComponent(player);
                bibliotecaAcceso.removeComponent(player);
                return "Entraste a la biblioteca";
            }else{
                if(player.getParent() == bibliotecaAcceso && (bibliotecaAcceso.contains(bibliotecario))){
                    gameEscape2.loseGame(player);
                }
            }
            return "No puedo";
        });

        PlayerCommand moveLibroViejo = new PlayerCommand("move LibroViejo");
        moveLibroViejo.setPlayerCommand((Container player) -> {
            if (player.getParent() == biblioteca && biblioteca.contains(libroViejo)) {
                biblioteca.removeComponent(libroViejo);
                return "Apareció el sotano";
            }
            return "Que libro??";
        });

        PlayerCommand gotoSotano = new PlayerCommand("goto Sotano");
        gotoSotano.setPlayerCommand((Container player) -> {
            if (player.getParent() == biblioteca && !biblioteca.contains(libroViejo)) {
                biblioteca.removeComponent(player);
                sotano.setComponent(player);
                return "Entraste al sotano";
            }
            return "Que Sotano??";
        });

        PlayerCommand useBaranda = new PlayerCommand("use Baranda");
        useBaranda.setPlayerCommand((Container player) -> {
            if (player.getParent() == sotano) {
                sotano.removeComponent(player);
                sotanoAbajo.setComponent(player);
                if (!player.contains(martillo)) {
                    //gameEscape2.loseGame();
                    return "Olvidaste el martillo";
                }
                return "Bajaste un piso en el sotano";
            }
            return "Que Baranda??";
        });

        PlayerCommand useEscalera = new PlayerCommand("use Escalera");
        useEscalera.setPlayerCommand((Container player) -> {
            if (player.getParent() == sotano) {
                sotano.removeComponent(player);
                //sotanoAbajo.setComponent(player);
                gameEscape2.loseGame(player);
                return "Se rompió el escalon, moriste!";
            }
            return "Que Escalera??";
        });

        PlayerCommand breakVentana = new PlayerCommand("break Ventana using Martillo");
        breakVentana.setPlayerCommand((Container player) -> {
            if (player.getParent() == sotanoAbajo && player.contains(martillo)) {
                sotanoAbajo.removeComponent(ventana);
                sotanoAbajo.setComponent(ventanaRota);
                return "Rompista la ventana!";
            }
            return "Que Ventana??";
        });

        PlayerCommand gotoAfuera = new PlayerCommand("goto Afuera");
        gotoAfuera.setPlayerCommand((Container player) -> {
            if (player.getParent() == sotanoAbajo && sotanoAbajo.contains(ventanaRota)) {
                sotanoAbajo.removeComponent(player);
                afuera.setComponent(player);
                return "Escape!";
            }
            return "No puedo";
        });


        RandomCommand bibliotecarioMove = new RandomCommand("bibliotecarioMove");

        Command bibliotecarioToBibliotecaAcceso = new Command("bibliotecarioToBibliotecaAcceso");
        Command bibliotecarioToPasillo = new Command("bibliotecarioToPasillo");
        Command bibliotecarioToBiblioteca = new Command("bibliotecarioToBiblioteca");
        Command bibliotecarioToSotano = new Command("bibliotecarioToSotano");


        bibliotecarioToBibliotecaAcceso.setExecutableCommand((HashMap<String, Container> components)-> {
            bibliotecarioMove.activeCommand(bibliotecarioToPasillo);
            bibliotecarioMove.activeCommand(bibliotecarioToBiblioteca);
            bibliotecarioMove.desactiveCommand(bibliotecarioToBibliotecaAcceso);
            bibliotecarioMove.desactiveCommand(bibliotecarioToSotano);
            if(pasillo.contains(bibliotecario)){
                pasillo.removeComponent(bibliotecario);
            }else{
                biblioteca.removeComponent(bibliotecario);
            }
            bibliotecaAcceso.setComponent(bibliotecario);
            return "El bibliotecario se movio a Biblioteca Acceso";
        });

        bibliotecarioToPasillo.setExecutableCommand((HashMap<String, Container> components)-> {
            bibliotecarioMove.desactiveCommand(bibliotecarioToPasillo);
            bibliotecarioMove.desactiveCommand(bibliotecarioToBiblioteca);
            bibliotecarioMove.activeCommand(bibliotecarioToBibliotecaAcceso);
            bibliotecarioMove.desactiveCommand(bibliotecarioToSotano);
            bibliotecaAcceso.removeComponent(bibliotecario);
            pasillo.setComponent(bibliotecario);
            return "El bibliotecario se movio al Pasillo";
        });

        bibliotecarioToBiblioteca.setExecutableCommand((HashMap<String, Container> components)-> {
            bibliotecarioMove.desactiveCommand(bibliotecarioToPasillo);
            bibliotecarioMove.desactiveCommand(bibliotecarioToBiblioteca);
            bibliotecarioMove.activeCommand(bibliotecarioToBibliotecaAcceso);
            if(!biblioteca.contains(libroViejo)){
                bibliotecarioMove.activeCommand(bibliotecarioToSotano);
            }else{
                bibliotecarioMove.desactiveCommand(bibliotecarioToSotano);
            }

            if(bibliotecaAcceso.contains(bibliotecario)){
                bibliotecaAcceso.removeComponent(bibliotecario);
            }else{
                sotano.removeComponent(bibliotecario);
            }

            biblioteca.setComponent(bibliotecario);

            for(Container player : gameEscape2.getPlayers()){
                if(player.getParent() == biblioteca && !player.contains(credencial)){
                    gameEscape2.loseGame(player);
                }
            }

            return "El bibliotecario se movio a la Biblioteca";
        });

        bibliotecarioToSotano.setExecutableCommand((HashMap<String, Container> components)-> {
            bibliotecarioMove.desactiveCommand(bibliotecarioToPasillo);
            bibliotecarioMove.activeCommand(bibliotecarioToBiblioteca);
            bibliotecarioMove.desactiveCommand(bibliotecarioToBibliotecaAcceso);
            bibliotecarioMove.desactiveCommand(bibliotecarioToSotano);
            biblioteca.removeComponent(bibliotecario);
            sotano.setComponent(bibliotecario);

            for(Container player : gameEscape2.getPlayers()){
                if(player.getParent() == sotano && !player.contains(credencial)){
                    gameEscape2.loseGame(player);
                }
            }

            return "El bibliotecario se movio al Sotano";
        });

        bibliotecarioMove.addOptionCommand(bibliotecarioToBibliotecaAcceso);
        bibliotecarioMove.addOptionCommand(bibliotecarioToPasillo);
        bibliotecarioMove.addOptionCommand(bibliotecarioToBiblioteca);
        bibliotecarioMove.addOptionCommand(bibliotecarioToSotano);

        bibliotecarioMove.desactiveCommand(bibliotecarioToBibliotecaAcceso);
        bibliotecarioMove.desactiveCommand(bibliotecarioToSotano);




        PlayerCommand giveLicor = new PlayerCommand("give BotellaLicor in Bibliotecario");
        giveLicor.setPlayerCommand((Container player) -> {
            if(player.getParent().contains(bibliotecario)) {
                if (player.contains(botellaLicor)) {
                    bibliotecario.removeComponent(energia);

                    gameEscape2.addTimedEvent(false, 2, () -> {
                        bibliotecario.setComponent(energia);
                        gameEscape2.addTimedEvent(true, 4,bibliotecarioMove);
                        return "El bibliotecario se desperto enojado!";
                    });

                    return "Se tomo todo el Licor";
                } else {
                    return "No tengo el Licor";
                }
            }
            return "No estoy en la misma habitacion que el biblotecario";
        });



        gameEscape2.setPlayerCommand(pickFoto);
        gameEscape2.setPlayerCommand(gotoBibliotecaAcceso);
        gameEscape2.setPlayerCommand(gotoSalon1);
        gameEscape2.setPlayerCommand(gotoSalon2);
        gameEscape2.setPlayerCommand(gotoSalon3);
        gameEscape2.setPlayerCommand(gotoPasillo);
        gameEscape2.setPlayerCommand(pickLLave);
        gameEscape2.setPlayerCommand(pickMartillo);
        gameEscape2.setPlayerCommand(pickBotellaLicor);
        gameEscape2.setPlayerCommand(moveCuadroBarco);
        gameEscape2.setPlayerCommand(openCajaFuerte);
        gameEscape2.setPlayerCommand(pickCredencial);
        gameEscape2.setPlayerCommand(putFotoInCredencial);
        gameEscape2.setPlayerCommand(showCredencial);
        gameEscape2.setPlayerCommand(gotoBiblioteca);
        gameEscape2.setPlayerCommand(moveLibroViejo);
        gameEscape2.setPlayerCommand(gotoSotano);
        gameEscape2.setPlayerCommand(useBaranda);
        gameEscape2.setPlayerCommand(useEscalera);
        gameEscape2.setPlayerCommand(breakVentana);
        gameEscape2.setPlayerCommand(gotoAfuera);
        gameEscape2.setPlayerCommand(giveLicor);
        gameEscape2.setExecutableCommands(help);
        gameEscape2.setWinCondition((Container player) -> (afuera.contains(player)));


        gameEscape2.setMaxPlayers(4);
        return gameEscape2;

    }
}