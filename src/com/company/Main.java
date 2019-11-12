package com.company;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] input;
        Scanner scanner = new Scanner(System.in);
        Grid Grid = new Grid(5,5);
        PacMan PacMan = new PacMan();
        Util Util = new Util();
        boolean isStarted = false;
        System.out.println("Welcome to PacMan world, please place your PacMan first " +
                "and enter a valid command to control PacMan or enter EXIT to quit the game");
        while(true) {
            Grid.printArray();
            System.out.print("Please enter your command: ");
            input = scanner.nextLine().split("\\s+");

            if(!Util.checkInput(input)){
                System.out.print("Please enter a valid command");
                System.out.println();
                continue;
            }
            String op = input[0];

            if(!isStarted) {
                if(!op.equals("PLACE")) {
                    System.out.println("Invalid command ! Please PLACE your PacMan first using \"PLACE X,Y,F\"");
                    continue;
                } else{
                    isStarted = true;
                }
            }

            if(op.equals("EXIT")) {
                System.out.println("Game terminated");
                break;
            }
            else if (op.equals("PLACE")) {
                String[] pos = input[1].split(",");
                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);
                //if it is a valid place operation, update pacman pos
                if(Grid.placePacMan(x,y)) {
                     PacMan.updatePacPos(new String[] {pos[0], pos[1], pos[2]});
                } else {
                    System.out.println("PLACE failed");
                }
            }
            else if (op.equals("MOVE")) {
                    String [] pacPos = PacMan.getCurrentPacPos();
                    String face = pacPos[2];
                    int x = Integer.parseInt(pacPos[0]);
                    int y = Integer.parseInt(pacPos[1]);
                    boolean isPlaced = false;

                    if(face.equals("NORTH")) {
                        isPlaced = Grid.placePacMan(x,y+1);
                    }
                    else if(face.equals("SOUTH")) {
                        isPlaced = Grid.placePacMan(x,y-1);
                    }
                    else if(face.equals("EAST")) {
                        isPlaced = Grid.placePacMan(x+1,y);
                    }
                    else if(face.equals("WEST")) {
                        isPlaced = Grid.placePacMan(x-1,y);
                    }

                    if(isPlaced) {
                        int [] gridPacPos = Grid.getGridPacPos();
                        String gridPacPosX = Integer.toString(gridPacPos[0]);
                        String gridPacPosY = Integer.toString(gridPacPos[1]);
                        PacMan.updatePacPos(new String[] {gridPacPosX, gridPacPosY, face});
                    } else {
                        System.out.println("MOVE failed.");
                    }

            }
            else if (op.equals("LEFT") || op.equals("RIGHT")) {
                String[] pacPos = PacMan.getCurrentPacPos();
                String currentDirection = pacPos[2];
                String nextDir = Util.getNextDir(currentDirection,op);
                if(!nextDir.equals(currentDirection)) {
                    PacMan.updateDir(nextDir);
                }
            }
            else if (op.equals("REPORT")) {
                System.out.println("Output: " + Arrays.toString(PacMan.getCurrentPacPos()));
            }
        }

    }
}
