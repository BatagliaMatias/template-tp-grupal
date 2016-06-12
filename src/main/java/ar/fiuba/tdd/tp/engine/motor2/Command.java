package ar.fiuba.tdd.tp.engine.motor2;

import java.util.HashMap;

public class Command implements Event {

    public String name;
    Executable executableCommand;
    private HashMap<String, Container> components = new HashMap<String, Container>();

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setExecutableCommand(Executable executableCommand) {
        this.executableCommand = executableCommand;
    }

    public void setExecutable(String componentName, String newStatus) {
        this.setExecutableCommand((HashMap<String, Container> components) -> {
                for (Container container : components.values()) {
                    String message = container.getDependantMessage();
                    if (message != "") {
                        return message;
                    }
                }
                return components.get(componentName).changeStatus(newStatus);
        });
    }

    public void setComponent(Container component) {
        this.components.put(component.getName(), component);
    }

    public String execute() {
        return this.executableCommand.execute(this.components);
    }

}
