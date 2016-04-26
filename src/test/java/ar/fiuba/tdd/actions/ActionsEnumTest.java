package ar.fiuba.tdd.actions;

import ar.fiuba.tdd.tp.shared.actions.ActionsEnum;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by jorlando on 26/04/16.
 */
public class ActionsEnumTest{

    @Test
    public void testGetName() {
        assertEquals(ActionsEnum.HELP.getName(), "help");
    }

    @Test
    public void testValueOfValidActionEnum() {
        assertEquals(ActionsEnum.getEnum("help"), ActionsEnum.HELP);
    }

    @Test
    public void testValueOfNullActionEnum() {
        assertEquals(ActionsEnum.getEnum(""), ActionsEnum.INVALID);
    }

    @Test
    public void testValueOfNotValidActionEnum() {
        assertEquals(ActionsEnum.getEnum("something"), ActionsEnum.INVALID);
    }
}