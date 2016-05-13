package ar.fiuba.tdd.tp.server.nuevomotor;

import java.awt.*;
import java.util.HashMap;

public class OpenDoor2 implements GameBuilder{

    @Override
    public Game build() {

        Game gameOpenDoor2 = new Game();

        Container box = new Container("Box");

        State boxStates = new State();

        boxStates.setModifier("open",(HashMap<String, Container> components,HashMap<String, Boolean> states)->{
            states.put("open",true);
            components.get("Key").changeStatus("visible");
            return "The box is open";
        });

        boxStates.setState("open",false);
        boxStates.setState("visible",true);

        box.setState(boxStates);

        Container key = new Container("Key");

        State keyStates = new State();

        keyStates.setState("picked",false);
        keyStates.setState("visible",false);

        keyStates.setModifier("pick",(HashMap<String, Container> components,HashMap<String, Boolean> states)->{
            states.put("picked",true);
            return "pick key";
        });
        keyStates.setModifier("visible",(HashMap<String, Container> components,HashMap<String, Boolean> states)->{
            states.put("visible",true);
            return "";
        });

        key.setState(keyStates);

        box.setComponent(key);

        Container door = new Container("Door");

        State doorState = new State();

        doorState.setState("open",false);
        doorState.setState("visible",true);

        doorState.setModifier("open",(HashMap<String, Container> components,HashMap<String, Boolean> states)->{
            states.put("open",true);
            return "The door is open";
        });

        door.setState(doorState);

        Command lookAt = new Command("look at");

        lookAt.setExecutableCommand((HashMap<String, Container> components)-> {
            StringBuffer buffer = new StringBuffer();
            buffer.append("There are ");
            for (String keys : components.keySet()) {
                if(components.get(keys).checkStatus("visible")){
                    buffer.append(keys+" ");
                }
            }
            return buffer.toString();
        });

        lookAt.setComponent(box);
        lookAt.setComponent(key);
        lookAt.setComponent(door);

        gameOpenDoor2.setExecutableCommands(lookAt);


        Command openBox = new Command("open box");

        openBox.setComponent(box);

        openBox.setExecutableCommand((HashMap<String, Container> components)-> components.get("Box").changeStatus("open"));

        gameOpenDoor2.setExecutableCommands(openBox);

        Command pickKey = new Command("pick key");

        pickKey.setComponent(key);

        pickKey.setExecutableCommand((HashMap<String, Container> components)-> {

            if(!components.get("Key").checkStatus("visible")) {
                return "which key?";
            }

            return components.get("Key").changeStatus("pick");

        });

        gameOpenDoor2.setExecutableCommands(pickKey);

        Command openDoor = new Command("open door");

        openDoor.setComponent(key);
        openDoor.setComponent(door);

        openDoor.setExecutableCommand((HashMap<String, Container> components)-> {

            if(!components.get("Key").checkStatus("picked")) {
                return "Ey! Where do you go?! Room is locked";
            }

            return components.get("Door").changeStatus("open");

        });

        gameOpenDoor2.setExecutableCommands(openDoor);

        CommandWin win = new CommandWin();

        win.setComponent(door);
        win.setWinnableCommand((HashMap<String, Container> components)-> components.get("Door").checkStatus("open"));

        gameOpenDoor2.setWinnersCommands(win);


        return gameOpenDoor2;
    }
}
