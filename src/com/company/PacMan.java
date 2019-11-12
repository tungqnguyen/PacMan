package com.company;

public class PacMan {
    private String[] pacPos;
    public PacMan() {
        pacPos= new String[3];
    }
    public String[] getCurrentPacPos() {
        return pacPos;
    }
    public void updateDir(String dir) {
        this.pacPos[2] = dir;
    }
    public void updatePacPos(String[] command){
            pacPos[0]=command[0];
            pacPos[1]=command[1];
            pacPos[2]=command[2];
    }
}
