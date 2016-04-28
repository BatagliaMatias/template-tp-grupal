package ar.fiuba.tdd.tp.server.motor.games;


import ar.fiuba.tdd.tp.server.motor.commands.Exit;
import ar.fiuba.tdd.tp.server.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.server.motor.commands.Help;
import ar.fiuba.tdd.tp.server.motor.commands.WhatCanIDoWith;
import ar.fiuba.tdd.tp.shared.Message;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    private static final String helpMessage = "Default Help";
    protected List<GameCommand> commands = new ArrayList<GameCommand>();

    public static String getHelp() {
        return helpMessage;
    }

    abstract boolean isGameOver();

    abstract String getGameOverMessage();

    public String getWelcomeMessage() {
        commands.add(new Help());
        commands.add(new Exit());
        String gameName = this.getGameName();
        return Message.WELCOME.getText().concat(gameName);
    }

    public String getGameName() {
        //Obtengo el nombre de la clase. Esto lo toma de la clase que se crea posta (OpenDoor2), no de Game.
        return this.getClass().getSimpleName();
    }

    /* template method : */
    public String processInput(String input) {

        for (GameCommand command : commands) {
            if (command.canProcessRequest(input)) {
                String response = command.execute();
                if (isGameOver()) {
                    response = getGameOverMessage();
                }
                return response;
            }
        }
        return "Invalid command";
    }

    protected void includeWhatCanIdoWithCommand() {
        List<String> computedTargets = new ArrayList<String>();
        List<String> avaiableCommandsIds = new ArrayList<String>();
        List<GameCommand> commandsToAd = new ArrayList<GameCommand>();

        for (GameCommand command : commands) {
            String target = command.getTargetName();
            if (computedTargets.contains(target) || (target.isEmpty())) {
                continue;
            }

            for (GameCommand otherCommand : commands) {
                if (otherCommand.getTargetName().equals(target)) {
                    avaiableCommandsIds.add(otherCommand.getIdentifier());
                }
            }
            commandsToAd.add(new WhatCanIDoWith(target, avaiableCommandsIds));
            computedTargets.add(target);
            avaiableCommandsIds = new ArrayList<String>();
        }
        commands.addAll(commandsToAd);
    }
}

