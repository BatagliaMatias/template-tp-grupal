package ar.fiuba.tdd.tp.engine.motor2;

import ar.fiuba.tdd.tp.shared.Message;

/**
 * Created by jorlando on 19/05/16.
 */
public enum GameState {
    Ready(""),
    InProgress(""),
    End(""),
    Won(Message.WIN.getText()),
    Lost(Message.LOST.getText());


    private String message;

    GameState(String newMesage) {
        this.message = newMesage;
    }

    public String getMessage() {
        return this.message;
    }
}
