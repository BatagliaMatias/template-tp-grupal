package ar.fiuba.tdd.tp.server.motor.commands;

import java.util.List;

public class WhatCanIDoWith extends GameCommand {

    private static final String SEPARATOR = "/ ";
    private final String target;
    private final List<String> commandsIdentifiers;

    public WhatCanIDoWith(String target, List<String> commandsIdentifiers) {
        super("What can i do with " + target + "?", "");
        this.commandsIdentifiers = commandsIdentifiers;
        this.target = target;
    }

    @Override
    public String execute() {
        String result;
        if (commandsIdentifiers.isEmpty()) {
            result = "You cant do anything with " + target;
        } else {
            StringBuilder buf = new StringBuilder();
            buf.append("With ");
            buf.append(target);
            buf.append(" you can: ");
            String currentSeparator = "";
            for (String identifier : commandsIdentifiers) {
                buf.append(currentSeparator);
                buf.append(identifier);
                currentSeparator = SEPARATOR;
            }
            result = buf.toString();
        }
        return result;
    }
}

