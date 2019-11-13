package com.pacman;

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
    public void updatePacPos(String[] position){
            pacPos[0]=position[0];
            pacPos[1]=position[1];
            pacPos[2]=position[2];

    }
}
