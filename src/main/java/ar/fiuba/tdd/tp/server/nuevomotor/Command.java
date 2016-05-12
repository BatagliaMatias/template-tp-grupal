package ar.fiuba.tdd.tp.server.nuevomotor;

import java.util.ArrayList;
import java.util.HashMap;

public class Command {

    private HashMap<String, Container> components = new HashMap<String, Container>();
    Executable executableCommand;

    public void setExecutableCommand(Executable executableCommand){
        this.executableCommand = executableCommand;
    }
    public void setComponent(String name,Container component){
        this.components.put(name,component);
    }
    public String execute(){
        return this.executableCommand.execute(this.components);
    }

}
