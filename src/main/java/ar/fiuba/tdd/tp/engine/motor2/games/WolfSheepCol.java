package ar.fiuba.tdd.tp.engine.motor2.games;

import ar.fiuba.tdd.tp.engine.motor2.*;

public class WolfSheepCol implements GameBuilder {

    @Override
    public Game build() { // IN PROGRESSS
        Container stick = buildStick();
        Command pickStick = new Command("pick stick");
        pickStick.setComponent(stick);
        pickStick.setExecutable("Stick", "pick");

        CommonCommandFactory commonCommandFactory = new CommonCommandFactory();
        Command lookAt = commonCommandFactory.getLook("look at");
        lookAt.setComponent(stick);

        Command help = commonCommandFactory.help("help", "You have to pick that stick over there.");

        Game gamePickStick = new Game();
        gamePickStick.addTimedEvent(true,5000,()-> "hola!");
        gamePickStick.setExecutableCommands(help);
        gamePickStick.setExecutableCommands(lookAt);
        gamePickStick.setExecutableCommands(pickStick);
        gamePickStick.setCommandWin(stick, "picked");

        return gamePickStick;
    }

    private Container buildStick() {
        State stickStates = new State();
        stickStates.setState("picked", false);
        stickStates.setState("visible", true);
        stickStates.setLamdaModifierByCommandAndState("pick", "picked", "stick picked");
        stickStates.setLamdaModifierByCommandAndState("visible", "visible", "");

        Container stick = new Container("Stick");
        stick.setState(stickStates);
        stick.setDependencies(new ContainerDependant(stick, "visible", "which stick?"));
        return stick;
    }
}
