package ar.fiuba.tdd.tp.engine.motor2;

/**
 * Created by jorlando on 13/05/16.
 */
public class ContainerDependant {

    Container container;
    String stateEnabler;
    String unsuccessfulMessage;

    public ContainerDependant(Container myContainer, String state, String message) {
        this.container = myContainer;
        this.stateEnabler = state;
        this.unsuccessfulMessage = message;
    }

    public String getUnsuccessfulMessage() {
        return this.unsuccessfulMessage;
    }

    public boolean isDependantAvailable() {
        return this.container.checkStatus(this.stateEnabler);
    }
}
