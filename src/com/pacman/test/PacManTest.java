package com.pacman.test;
import com.pacman.PacMan;
import com.pacman.Util;
import com.pacman.Grid;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class PacManTest {
    Util Util = new Util();
    PacMan PacMan = new PacMan();
    Grid Grid = new Grid(5,5);

    //Util tests
    @Test
    public void testCheckInput() {
        boolean result;
        String[] input;

        input = new String[]{"EXIT"};
        result = Util.checkInput(input);
        assertTrue(result);

        input = new String[]{"JUMP", "1,2,NORTH"};
        result = Util.checkInput(input);
        assertFalse(result);

        input = new String[]{"PLACE", "1,2,", "INVALID"};
        result = Util.checkInput(input);
        assertFalse(result);

        input = new String[]{"PLACE", "5,5,SOUTHWEST"};
        result = Util.checkInput(input);
        assertFalse(result);

        input = new String[]{"MOVE", "5,5,SOUTHWEST"};
        result = Util.checkInput(input);
        assertFalse(result);

        input = new String[]{"PLACE", ",1,5,5,not working"};
        result = Util.checkInput(input);
        assertFalse(result);

        input = new String[]{"PLACE", "4,4,WEST"};
        result = Util.checkInput(input);
        assertTrue(result);
    }

    @Test
    public void testGetNextDir() {
        String result;

        result = Util.getNextDir("WEST","RIGHT");
        assertEquals("NORTH",result);

        result = Util.getNextDir("NORTH","LEFT");
        assertEquals("WEST",result);

        result = Util.getNextDir("EAST","RIGHT");
        assertEquals("SOUTH",result);

        result = Util.getNextDir("SOUTH","LEFT");
        assertEquals("EAST",result);
    }

    //PacMan tests
    @Test
    public void testPacManMethods() {
        PacMan.updateDir("NORTH");
        assertEquals("NORTH", PacMan.getCurrentPacPos()[2]);

        PacMan.updatePacPos(new String[] {"1","2","EAST"});
        assertArrayEquals(new String[] {"1","2","EAST"}, PacMan.getCurrentPacPos());
    }

    //Grid tests
    @Test
    public void testGridMethods() {
       ByteArrayOutputStream outContent = new ByteArrayOutputStream();
       System.setOut(new PrintStream(outContent));
       boolean result;
       result = Grid.placePacMan(4,5);
       assertFalse(result);
       assertEquals("PacMan is moving off the grid! ", outContent.toString());

       result = Grid.placePacMan(2,3);
       assertTrue(result);
       int[] pacPos = Grid.getGridPacPos();
       assertArrayEquals(new int[] {2,3}, pacPos);

    }
}