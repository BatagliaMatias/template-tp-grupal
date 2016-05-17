package ar.fiuba.tdd.tp.engine.motor2;

import java.util.HashMap;

public interface Modifiable {
    String change(HashMap<String, Container> components,HashMap<String, Boolean> states);
}
