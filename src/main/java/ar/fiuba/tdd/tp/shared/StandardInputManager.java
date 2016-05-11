package ar.fiuba.tdd.tp.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by jorlando on 28/04/16.
 */
public class StandardInputManager {
    static final String ENCODING = "UTF-8";
    BufferedReader standardInput = null;

    public StandardInputManager() {
        try {
            standardInput = new BufferedReader(new InputStreamReader(System.in, ENCODING));
        } catch (UnsupportedEncodingException e) {
            System.out.println(Message.INPUT_ERROR.getText());
        }
    }

    public String read() {
        try {
            return standardInput.readLine();
        } catch (IOException e) {
            return Message.INPUT_ERROR.getText();
        }
    }

    public void close() {
        try {
            standardInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
