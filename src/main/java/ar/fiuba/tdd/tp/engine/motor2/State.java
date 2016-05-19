package ar.fiuba.tdd.tp.engine.motor2;

import java.util.HashMap;

public class State {

    private HashMap<String, Boolean> states = new HashMap<String, Boolean>();
    private HashMap<String, Modifiable> modifiers = new HashMap<String, Modifiable>();

    public void setState(String state,boolean condition) {
        this.states.put(state,condition);
    }

    public void setModifier(String command,Modifiable modifier) {
        this.modifiers.put(command,modifier);
    }

    public String changeStates(HashMap<String, Container> components,String command) {
        return this.modifiers.get(command).change(components,this.states);
    }

    public boolean checkStatus(String state) {
        return ((this.states.containsKey(state)) ? (this.states.get(state)) : (false));
    }

    public void setLamdaModifierByCommandAndState(String command, String stateAdd, String message) {
        this.setModifier(command,(HashMap<String, Container> components,HashMap<String, Boolean> states)-> {
                states.put(stateAdd,true);
                return message;
            });

    }

}
