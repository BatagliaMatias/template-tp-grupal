package ar.fiuba.tdd.tp.server.nuevomotor;

import java.util.ArrayList;
import java.util.HashMap;

public class Container {

    private HashMap<String, Container> componentsContained = new HashMap<String, Container>();
    State states;
    private String name;

    public Container(String name){
        this.name = name;
    }

    public void setState(State state){
        this.states = state;
    }

    public String getName(){
        return this.name;
    }

    public void setComponent(String name,Container component){
        this.componentsContained.put(name,component);
    }

    public String changeStatus(String state){
        return this.states.changeStates(this.componentsContained,state);
    }

    public boolean checkStatus(String state){
        return this.states.checkStatus(state);
    }

}
