package ar.fiuba.tdd.tp.server.nuevomotor;

import java.util.HashMap;

public interface Executable {
    String execute(HashMap<String, Container> components);
}
