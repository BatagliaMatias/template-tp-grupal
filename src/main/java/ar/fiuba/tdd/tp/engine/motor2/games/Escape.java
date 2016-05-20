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

        Command help = commonCommandFactory.help("help", "Tenes que escapar por el sotano de la biblioteca");

        Game gameEscape = new Game();
        gameEscape.setExecutableCommands(help);

        //personaje
        Container personaje = new Container("personaje");
        Container foto = new Container("foto");
        personaje.setComponent(foto);

        State personajeStates = new State();
        personajeStates.setState("gano",false);
        personajeStates.setLamdaModifierByCommandAndState("gano", "gano", "gano");


        personaje.setState(personajeStates);


        //lugares
        Container pasillo = new Container("pasillo");
        pasillo.setComponent(personaje);
        Container salon1 = new Container("salon1");
        Container salon2 = new Container("salon2");
        Container salon3 = new Container("salon3");
        Container bibliotecaAcceso = new Container("bibliotecaAcceso");
        Container biblioteca = new Container("biblioteca");
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
        Container bibliotecario = new Container("bibliotecario");
        bibliotecaAcceso.setComponent(bibliotecario);

        //Bibloteca
        Container libroViejo = new Container("libroViejo");
        biblioteca.setComponent(libroViejo);

        //Sotano
        Container escalera = new Container("escalera");
        Container baranda = new Container("baranda");
        sotano.setComponent(escalera);
        sotano.setComponent(baranda);

        //SotanoAbajo
        Container ventana = new Container("ventana");
        Container ventanaRota = new Container("ventanaRota");
        sotanoAbajo.setComponent(ventana);

        //comandos

        Command gotoBibliotecaAcceso = new Command("goto BibliotecaAcceso");
        gotoBibliotecaAcceso.setExecutableCommand((HashMap<String, Container> components)-> {
            if (pasillo.contains(personaje)) {
                pasillo.removeComponent(personaje);
                bibliotecaAcceso.setComponent(personaje);
                return "moved";
            }

            return "no me puedo mover";

        });

        Command gotoSalon1 = new Command("goto Salon1");
        gotoSalon1.setExecutableCommand((HashMap<String, Container> components)-> {
            if (pasillo.contains(personaje)) {
                pasillo.removeComponent(personaje);
                salon1.setComponent(personaje);
                return "moved";
            }

            return "no me puedo mover";

        });

        Command gotoSalon2 = new Command("goto Salon2");
        gotoSalon2.setExecutableCommand((HashMap<String, Container> components)-> {
            if (pasillo.contains(personaje)) {
                pasillo.removeComponent(personaje);
                salon2.setComponent(personaje);
                return "moved";
            }

            return "no me puedo mover";


        });

        Command gotoSalon3 = new Command("goto Salon3");
        gotoSalon3.setExecutableCommand((HashMap<String, Container> components)-> {
            if (pasillo.contains(personaje)) {
                pasillo.removeComponent(personaje);
                salon3.setComponent(personaje);
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
            origenes.add(bibliotecaAcceso);
            for (Container origen : origenes){
                if (origen.contains(personaje)) {
                    origen.removeComponent(personaje);
                    pasillo.setComponent(personaje);
                    return "moved";
                }
            }

            return "no me puedo mover";


        });

        gameEscape.setExecutableCommands(gotoBibliotecaAcceso);
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

        Command showCredencial = new Command("show Credencial in Bibliotecario");
        showCredencial.setExecutableCommand((HashMap<String, Container> components)-> {
            if(bibliotecaAcceso.contains(personaje) && personaje.contains(credencial) && credencial.contains(foto)){
                bibliotecario.setComponent(credencial);
                return "Linda credencial";
            }
            return "no tenes credencial con foto";
        });

        gameEscape.setExecutableCommands(showCredencial);

        Command gotoBiblioteca = new Command("goto Biblioteca");
        gotoBiblioteca.setExecutableCommand((HashMap<String, Container> components)-> {
            if(bibliotecaAcceso.contains(personaje) && bibliotecario.contains(credencial)){ // o el vino
                biblioteca.setComponent(personaje);
                bibliotecaAcceso.removeComponent(personaje);
                return "En la biblioteca";
            }
            return "raja de aca";
        });

        gameEscape.setExecutableCommands(gotoBiblioteca);

        Command moveLibroViejo = new Command("move LibroViejo");
        moveLibroViejo.setExecutableCommand((HashMap<String, Container> components)-> {
            if(biblioteca.contains(personaje) && biblioteca.contains(libroViejo)){ // o el vino
                biblioteca.removeComponent(libroViejo);
                return "Apareció el sotano";
            }
            return "Que libro??";
        });

        gameEscape.setExecutableCommands(moveLibroViejo);

        Command gotoSotano = new Command("goto Sotano");
        gotoSotano.setExecutableCommand((HashMap<String, Container> components)-> {
            if(biblioteca.contains(personaje) && !biblioteca.contains(libroViejo)){
                biblioteca.removeComponent(personaje);
                sotano.setComponent(personaje);
                return "Entraste al sotano";
            }
            return "Que Sotano??";
        });

        gameEscape.setExecutableCommands(gotoSotano);

        Command useBaranda = new Command("use Baranda");
        useBaranda.setExecutableCommand((HashMap<String, Container> components)-> {
            if(sotano.contains(personaje)){
                sotano.removeComponent(personaje);
                sotanoAbajo.setComponent(personaje);
                return "Bajaste un piso en el sotano";
            }
            return "Que Baranda??";
        });

        gameEscape.setExecutableCommands(useBaranda);

        Command breakVentana = new Command("break Ventana using Martillo");
        breakVentana.setExecutableCommand((HashMap<String, Container> components)-> {
            if(sotanoAbajo.contains(personaje) && personaje.contains(martillo)){
                sotanoAbajo.removeComponent(ventana);
                sotanoAbajo.setComponent(ventanaRota);
                return "Rompiste la ventana!";
            }
            return "Que Ventana??";
        });

        gameEscape.setExecutableCommands(breakVentana);

        Command gotoAfuera = new Command("goto Afuera");
        gotoAfuera.setExecutable("personaje", "gano");
        gotoAfuera.setExecutableCommand((HashMap<String, Container> components)-> {
            if(sotanoAbajo.contains(personaje) && sotanoAbajo.contains(ventanaRota)){
                sotanoAbajo.removeComponent(personaje);
                afuera.setComponent(personaje);
                personaje.changeStatus("gano");
                return "Me escape!!";
            }
            return "No puedo";
        });

        gameEscape.setExecutableCommands(gotoAfuera);
        gameEscape.setCommandWin(personaje, "gano");
        return gameEscape;
    }


}
