package com.pacman;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] input=null;
    static Grid Grid = new Grid(5,5);
    static PacMan PacMan = new PacMan();
    static Util Util = new Util();
    static boolean isStarted = false;

    static void runGamingMode() {
        System.out.println("Gaming mode selected! Enter EXIT to stop the game at anytime. ");
        while(true) {
            Grid.printArray();
            System.out.print("Please enter your command: ");
            if(scanner.hasNext()) {
                input = scanner.nextLine().split("\\s+");
            }
            String op = input[0];

            if(op.equals("EXIT")) {
                System.out.println("Game terminated");
                break;
            }
            if(!Util.checkInput(input)){
                System.out.print("Please enter a valid command");
                System.out.println();
                continue;
            }

            if(!isStarted) {
                if(!op.equals("PLACE")) {
                    System.out.println("Invalid command ! Please PLACE your PacMan first using \"PLACE X,Y,F\"");
                    continue;
                } else{
                    isStarted = true;
                }
            }

            if (op.equals("PLACE")) {
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

    public static void runNormalMode(File file) {
        System.out.println("Normal mode selected! ");
        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            while (sc.hasNextLine()){
                input = sc.nextLine().split("\\s+");
                if(!Util.checkInput(input)){
                    continue;
                }
                String op = input[0];
                if(!isStarted) {
                    if(!op.equals("PLACE")) {
                        continue;
                    } else{
                        isStarted = true;
                    }
                }

                if (op.equals("PLACE")) {
                    String[] pos = input[1].split(",");
                    int x = Integer.parseInt(pos[0]);
                    int y = Integer.parseInt(pos[1]);
                    //if it is a valid place operation, update pacman pos
                    if(Grid.placePacMan(x,y)) {
                        PacMan.updatePacPos(new String[] {pos[0], pos[1], pos[2]});
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
                    System.out.println("\nOutput: " + Arrays.toString(PacMan.getCurrentPacPos()));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int selection=-1;
        System.out.println("Welcome to PacMan world, please place your PacMan first " +
                "and enter a valid command to control PacMan");
        while(selection==-1) {
            System.out.print("Please choose \"gaming mode\"(1) or \"normal mode\"(2): ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if(choice == 1 || choice == 2) selection = choice;
                else System.out.println("Invalid mode");
            } else {
                scanner.nextLine();
                System.out.println("please enter valid number");
            }
        }
        if (selection==1)  runGamingMode();
        else {
            File file = new File("src/com/pacman/test/test_case/test3.txt");
            runNormalMode(file);
        }
    }
}
