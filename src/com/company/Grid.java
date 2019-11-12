package com.company;

import java.util.*;

public class Grid {
    private String[] directions={"NORTH", "WEST", "EAST", "SOUTH"};
    private String[] validCommands={"PLACE","LEFT","RIGHT","MOVE","REPORT","EXIT"};
    public String grid[][];
    private int[] currentPacPos = null;
    private int row, col;

    public Grid(int col, int row) {
        this.grid = new String[row][col];
        this.initArray(col, row);
        this.row = row;
        this.col = col;
    }
    public String[] getAvailableDirections() {
        return directions;
    }

    public int[] getGridPacPos() {
        return this.currentPacPos;
    }
    private void initArray(int col, int row) {
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++){
                this.grid[i][j]=".";
            }
        }
    }

    public boolean checkInput(String[] input) {
        System.out.println("Input "+ Arrays.toString(input));
        if(input[0].equals("EXIT")) return true;
        else if (!Arrays.asList(this.validCommands).contains(input[0])) {System.out.print("Invalid operation! "); return false;}
        else if(input[0].equals("PLACE") ) {
            if(input.length != 4) {
                System.out.print("Invalid PLACE operation! ");
                return false;
            }
            if (!(0<=Integer.parseInt(input[1]) && Integer.parseInt(input[1])<5 && 0<=Integer.parseInt(input[2])
                    && Integer.parseInt(input[2])<5 && Arrays.asList(directions).contains(input[3]))) {
                System.out.print("Invalid PLACE operation! ");
                return false;
            }
        }
        else if ((input[0].equals("MOVE") || input[0].equals("LEFT") || input[0].equals("RIGHT") || input[0].equals("REPORT"))
                && input.length>1) {
            System.out.print("Too many operators! ");
            return false;
        }
        return true;
    }
    public void printArray() {
        System.out.print("  ");
        for(int i=0;i<this.grid.length;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i=this.grid.length-1;i>=0;i--) {
            System.out.print(i+" ");
            for(int j=0;j<this.grid[i].length;j++) {
                System.out.print(this.grid[i][j]+" ");
            }
            System.out.println();
        }
    }
    public boolean placePacMan(int x, int y) {
        System.out.println("x: "+x+" y: "+y);
        if(x>= this.row || x <0 || y >= this.col || y<0) {
            System.out.print("PacMan is moving off the grid! ");
            return false;
        }
        //if PacMan is not placed yet
        if(this.currentPacPos == null) {
            System.out.println("init " + Arrays.deepToString(this.grid));
            this.currentPacPos = new int[2];
//            this.currentPacPos[0]=x;
//            this.currentPacPos[1]=y;
        // if PacMan is already placed, remove the previous position
        } else {
            System.out.println("pos "+ Arrays.toString(this.currentPacPos));
            this.grid[this.currentPacPos[0]][this.currentPacPos[1]] = ".";
            System.out.println("removed " + Arrays.deepToString(this.grid));
        }
        //update current pacPos
        this.grid[x][y] = "P";
        this.currentPacPos[0]=x;
        this.currentPacPos[1]=y;
        return true;
    }
}
