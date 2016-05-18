package ar.fiuba.tdd.tp.engine.motor2;

import java.util.HashMap;

/**
 * Created by mbataglia on 18/05/16.
 */
public class CommonCommandFactory {

    public Command getLook(String name){
        Command command = new Command(name);
        command.setExecutableCommand((HashMap<String, Container> components)-> {
            StringBuffer buffer = new StringBuffer();
            buffer.append("There are ");
            components.forEach((componentsName,container)-> {
                if (container.checkStatus("visible")) {
                    buffer.append(componentsName + " ");
                }
            });
            return buffer.toString();
        });
        return command;
    }

}
