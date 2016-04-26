package ar.fiuba.tdd.tp.shared.actions;

/**
 * Created by jorlando on 26/04/16.
 */
public enum ActionsEnum {
    LOAD("load"),
    HELP("help"),
    EXIT("exit"),
    CONNECT("connect"),
    INVALID("invalid");

    private final String name;

    ActionsEnum(String newName) {
        this.name = newName;
    }

    public static ActionsEnum getEnum(String nameToAnalize) {
        try {
            return ActionsEnum.valueOf(nameToAnalize.toUpperCase());
        } catch (IllegalArgumentException t) {
            return ActionsEnum.INVALID;
        }
    }

    public String getName() {
        return this.name;
    }
}
