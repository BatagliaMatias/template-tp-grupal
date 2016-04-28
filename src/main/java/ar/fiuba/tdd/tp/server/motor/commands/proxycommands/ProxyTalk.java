package ar.fiuba.tdd.tp.server.motor.commands.proxycommands;

import ar.fiuba.tdd.tp.server.motor.Player;
import ar.fiuba.tdd.tp.server.motor.Stage;
import ar.fiuba.tdd.tp.server.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.server.motor.commands.Talk;

public class ProxyTalk extends GameCommand{

    private Talk talk;
    private Player player;
    private Stage destinationRoom;

    public ProxyTalk(Talk talk,Player player,Stage destinationRoom) {

        super("proxy talk");

        this.talk = talk;
        this.player = player;
        this.destinationRoom = destinationRoom;

    }

    @Override
    public String execute() {

        return this.talk.execute();

    }

    @Override
    public boolean canProcessRequest(String request) {

        boolean correctMessage = this.talk.canProcessRequest(request);

        return correctMessage && this.player.getLocation() == this.destinationRoom;

    }

    @Override
    public String getIdentifier() {

        if ( this.player.getLocation() == this.destinationRoom ) {
            return this.talk.getIdentifier();
        }

        return "";
    }

}
