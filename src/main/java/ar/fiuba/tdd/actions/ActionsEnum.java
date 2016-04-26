package ar.fiuba.tdd.actions;

/**
 * Created by jorlando on 26/04/16.
 */
public enum ActionsEnum {
    LOAD ("load"),
    HELP ("help"),
    EXIT ("exit"),
    CONNECT ("connect"),
    INVALID ("invalid");

    private final String name;

    ActionsEnum(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }

    public static ActionsEnum getEnum(String nameToAnalize) {
        try {
            return ActionsEnum.valueOf(nameToAnalize.toUpperCase());
        } catch (IllegalArgumentException t) {
            return ActionsEnum.INVALID;
        }
    }
}
