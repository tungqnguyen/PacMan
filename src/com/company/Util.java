package com.company;

import java.util.Arrays;

public class Util {
    private  String[] directions={"WEST","NORTH","EAST","SOUTH"};
    private  String[] validCommands={"PLACE","LEFT","RIGHT","MOVE","REPORT","EXIT"};

    public boolean checkInput(String[] input) {
        try{
            if (input[0].equals("EXIT")) return true;
            else if (!Arrays.asList(this.validCommands).contains(input[0])) {
                System.out.print("Invalid operation! ");
                return false;
            } else if (input[0].equals("PLACE")) {
                if (input.length != 2) {
                    System.out.print("Invalid PLACE operation! ");
                    return false;
                }
                String[] position = input[1].split(",");
                if (!(0 <= Integer.parseInt(position[0]) && Integer.parseInt(position[0]) < 5 && 0 <= Integer.parseInt(position[1])
                        && Integer.parseInt(position[1]) < 5 && Arrays.asList(this.directions).contains(position[2]))) {
                    System.out.print("Invalid PLACE operation! ");
                    return false;
                }
            } else if ((input[0].equals("MOVE") || input[0].equals("LEFT") || input[0].equals("RIGHT") || input[0].equals("REPORT"))
                    && input.length > 1) {
                System.out.print("Too many operators! ");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.print("Invalid Syntax! ");
            return false;
        }
    }

    //calculate next direction according to LEFt or RIGHT
    public String getNextDir(String currentDir, String turn) {
        int idx=-1;
        for(int i=0;i<this.directions.length;i++) {
            if(currentDir.equals(this.directions[i])) {
                idx = i;
                break;
            }
        }
        if(idx !=-1 && turn.equals("LEFT")) {
            if(0<=idx-1) {
                return this.directions[idx-1];
            } else {
                return this.directions[this.directions.length-1];
            }
        }
        if(idx !=-1 && turn.equals("RIGHT")) {
            if(idx+1<=this.directions.length-1) {
                return this.directions[idx+1];
            } else {
                return this.directions[0];
            }
        }
        return currentDir;
    }
}
