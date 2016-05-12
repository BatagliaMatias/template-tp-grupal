package ar.fiuba.tdd.tp.server.nuevomotor;

import java.util.HashMap;

public class State {

    private HashMap<String, Boolean> states = new HashMap<String, Boolean>();
    private HashMap<String, Modifiable> modifiers = new HashMap<String, Modifiable>();

    public void setState(String state,boolean condition){
        this.states.put(state,condition);
    }

    public void setModifier(String comando,Modifiable modifier){
        this.modifiers.put(comando,modifier);
    }

    public String changeStates(HashMap<String, Container> components,String command){
        return this.modifiers.get(command).change(components,this.states);
    }

    public boolean checkStatus(String state){
        return this.states.get(state);
    }

}
