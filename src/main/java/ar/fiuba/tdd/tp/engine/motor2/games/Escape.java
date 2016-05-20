package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.engine.motor2.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mbataglia on 19/05/16.
 */
public class Escape implements GameBuilder {
    @Override
    public Game build() {

        CommonCommandFactory commonCommandFactory = new CommonCommandFactory();

        Command help = commonCommandFactory.help("help", "You have to pick that stick over there.");

        Game gameEscape = new Game();
        gameEscape.setExecutableCommands(help);

        //personaje
        Container personaje = new Container("personaje");
        Container foto = new Container("foto");
        personaje.setComponent(foto);

        //lugares
        Container pasillo = new Container("pasillo");
        Container salon1 = new Container("salon1");
        Container salon2 = new Container("salon2");
        Container salon3 = new Container("salon3");
        Container biblotecaAcceso = new Container("biblotecaAcceso");
        Container bibloteca = new Container("bibloteca");
        Container sotano = new Container("sotano");
        Container sotanoAbajo = new Container("sotanoAbajo");
        Container afuera = new Container("afuera");
        //salon1
        Container cuadroBarco = new Container("cuadroBarco");
        Container cajaFuerte = new Container("cajaFuerte");
        Container botellaLicor = new Container("botellaLicor");
        Container credencial = new Container("credencial");
        cuadroBarco.setComponent(cajaFuerte);
        salon1.setComponent(cuadroBarco);
        salon1.setComponent(botellaLicor);
        salon1.setComponent(credencial);

        //salon2
        Container martillo = new Container("martillo");
        salon2.setComponent(martillo);

        //salon3
        Container llave = new Container("llave");
        salon3.setComponent(llave);

        //BiblotecaAcceso
        Container biblotecario = new Container("biblotecario");
        biblotecaAcceso.setComponent(biblotecario);

        //Bibloteca
        Container libroViejo = new Container("libroViejo");
        bibloteca.setComponent(libroViejo);

        //Sotano
        Container escalera = new Container("escalera");
        Container baranda = new Container("baranda");
        sotano.setComponent(escalera);
        sotano.setComponent(baranda);

        //SotanoAbajo
        Container ventana = new Container("ventana");
        sotanoAbajo.setComponent(ventana);

        //comandos

        Command gotoBiblotecaAcceso = new Command("goto BiblotecaAcceso");
        gotoBiblotecaAcceso.setExecutableCommand((HashMap<String, Container> components)-> {
            if (pasillo.contains(personaje)) {
                pasillo.removeComponent(personaje);
                biblotecaAcceso.setComponent(personaje);
                return "moved";
            }

            return "no me puedo mover";

        });

        Command gotoSalon1 = new Command("goto Salon1");
        gotoSalon1.setExecutableCommand((HashMap<String, Container> components)-> {
            if (pasillo.contains(personaje)) {
                pasillo.removeComponent(personaje);
                gotoSalon1.setComponent(personaje);
                return "moved";
            }

            return "no me puedo mover";

        });

        Command gotoSalon2 = new Command("goto Salon2");
        gotoSalon2.setExecutableCommand((HashMap<String, Container> components)-> {
            if (pasillo.contains(personaje)) {
                pasillo.removeComponent(personaje);
                gotoSalon2.setComponent(personaje);
                return "moved";
            }

            return "no me puedo mover";


        });

        Command gotoSalon3 = new Command("goto Salon3");
        gotoSalon3.setExecutableCommand((HashMap<String, Container> components)-> {
            if (pasillo.contains(personaje)) {
                pasillo.removeComponent(personaje);
                gotoSalon3.setComponent(personaje);
                return "moved";
            }

            return "no me puedo mover";

        });

        Command gotoPasillo = new Command("goto Pasillo");
        gotoPasillo.setExecutableCommand((HashMap<String, Container> components)-> {
            ArrayList<Container> origenes = new ArrayList();
            origenes.add(salon1);
            origenes.add(salon2);
            origenes.add(salon3);
            origenes.add(biblotecaAcceso);
            for (Container origen : origenes){
                if (origen.contains(personaje)) {
                    origen.removeComponent(personaje);
                    gotoPasillo.setComponent(personaje);
                    return "moved";
                }
            }

            return "no me puedo mover";


        });

        gameEscape.setExecutableCommands(gotoBiblotecaAcceso);
        gameEscape.setExecutableCommands(gotoPasillo);
        gameEscape.setExecutableCommands(gotoSalon1);
        gameEscape.setExecutableCommands(gotoSalon2);
        gameEscape.setExecutableCommands(gotoSalon3);

        Command pickLlave = new Command("pick Llave");
        pickLlave.setExecutableCommand((HashMap<String, Container> components)-> {
            if(salon3.contains(personaje) && salon3.contains(llave)){
                personaje.setComponent(llave);
                salon3.removeComponent(llave);
                return "Tenes la llave";
            }
            return "Que llave?";
        });

        gameEscape.setExecutableCommands(pickLlave);

        Command pickMartillo = new Command("pick Martillo");
        pickMartillo.setExecutableCommand((HashMap<String, Container> components)-> {
            if(salon2.contains(personaje) && salon2.contains(martillo)){
                personaje.setComponent(martillo);
                salon2.removeComponent(martillo);
                return "Tenes el martillo";
            }
            return "Que martillo?";
        });

        gameEscape.setExecutableCommands(pickMartillo);

        Command moveCuadroBarco = new Command("move CuadroBarco");
        moveCuadroBarco.setExecutableCommand((HashMap<String, Container> components)-> {
            if(salon1.contains(personaje) && salon1.contains(cuadroBarco)){
                salon1.removeComponent(cuadroBarco);
                return "Moviste el cuadro";
            }
            return "Que CuadroBarco?";
        });

        gameEscape.setExecutableCommands(moveCuadroBarco);



        Command openCajaFuerte = new Command("open CajaFuerte using Llave");
        openCajaFuerte.setExecutableCommand((HashMap<String, Container> components)-> {
            if(salon1.contains(personaje) && !salon1.contains(cuadroBarco) && personaje.contains(llave)){
                salon1.removeComponent(cajaFuerte);
                return "Abriste la cajaFuerte!";
            }
            return "No podes";
        });

        gameEscape.setExecutableCommands(openCajaFuerte);

        Command pickCredencial = new Command("pick Credencial");
        pickCredencial.setExecutableCommand((HashMap<String, Container> components)-> {
            if(salon1.contains(personaje) && !salon1.contains(cajaFuerte) && salon1.contains(credencial)){
                salon1.removeComponent(credencial);
                personaje.setComponent(credencial);
                return "Tenes la credencial";
            }
            return "No puedo :(";
        });

        gameEscape.setExecutableCommands(pickCredencial);

        Command putFotoInCredencial = new Command("put Foto in Credencial");
        putFotoInCredencial.setExecutableCommand((HashMap<String, Container> components)-> {
            if(personaje.contains(foto) && personaje.contains(credencial)){
                credencial.setComponent(foto);
                personaje.removeComponent(foto);
                return "Tenes la credencial con la foto";
            }
            return "No puedo :/";
        });

        gameEscape.setExecutableCommands(putFotoInCredencial);

        Command showCredencial = new Command("show Credencial in Biblotecario");
        showCredencial.setExecutableCommand((HashMap<String, Container> components)-> {
            if(biblotecaAcceso.contains(personaje) && personaje.contains(credencial) && credencial.contains(foto)){
                biblotecario.setComponent(credencial);
                return "Linda credencial";
            }
            return "no tenes credencial con foto";
        });

        gameEscape.setExecutableCommands(showCredencial);

        Command gotoBibloteca = new Command("goto Biblioteca");
        gotoBibloteca.setExecutableCommand((HashMap<String, Container> components)-> {
            if(biblotecaAcceso.contains(personaje) && biblotecario.contains(credencial)){ // o el vino
                bibloteca.setComponent(personaje);
                biblotecaAcceso.removeComponent(personaje);
                return "En la biblioteca";
            }
            return "raja de aca";
        });

        gameEscape.setExecutableCommands(gotoBibloteca);

        return gameEscape;
    }


}
