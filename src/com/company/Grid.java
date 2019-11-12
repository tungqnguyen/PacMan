package com.company;

public class Grid {

    private String grid[][];
    private int[] currentPacPos = null;
    private int row, col;

    public Grid(int col, int row) {
        this.grid = new String[col][row];
        this.initArray(col, row);
        this.row = row;
        this.col = col;
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
        if(x>= this.row || x <0 || y >= this.col || y<0) {
            System.out.print("PacMan is moving off the grid! ");
            return false;
        }
        //if PacMan is not placed yet
        if(this.currentPacPos == null) {
            this.currentPacPos = new int[2];
        // if PacMan is already placed, remove the previous position
        } else {
            this.grid[this.currentPacPos[1]][this.currentPacPos[0]] = ".";
        }
        //update current pacPos
        this.grid[y][x] = "P";
        this.currentPacPos[0]=x;
        this.currentPacPos[1]=y;
        return true;
    }
}
