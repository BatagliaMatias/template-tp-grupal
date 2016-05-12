package ar.fiuba.tdd.tp.server.nuevomotor;

import java.util.HashMap;

public interface Modifiable {
    String change(HashMap<String, Container> components,HashMap<String, Boolean> states);
}
