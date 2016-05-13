package ar.fiuba.tdd.tp.server.nuevomotor;

import java.util.ArrayList;
import java.util.HashMap;

public class Command {

    private HashMap<String, Container> components = new HashMap<String, Container>();
    Executable executableCommand;
    public String name;

    public Command(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setExecutableCommand(Executable executableCommand){
        this.executableCommand = executableCommand;
    }
    public void setComponent(Container component){
        this.components.put(component.getName(),component);
    }
    public String execute(){
        return this.executableCommand.execute(this.components);
    }

}
