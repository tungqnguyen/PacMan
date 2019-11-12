package com.company;

public class PacMan {
    private String[] pacPos;
    public PacMan() {
        pacPos= new String[3];
    }
    public String[] getCurrentPacPos() {
        return pacPos;
    }

    private boolean movePacMan() {

        return true;
    }
    public boolean updatePacPos(String[] command){
//        if(!isStarted) {
//            if(!command[0].equals("PLACE")) {
//                System.out.println("Invalid command ! Please PLACE your PacMan first using \"PLACE X Y F\"");
//                return false;
//            } else{
//                isStarted = true;
//            }
//        }

//        if (command[0].equals("PLACE")){
            pacPos[0]=command[0];
            pacPos[1]=command[1];
            pacPos[2]=command[2];

//        }

        return true;
    }
}
