package com.echec.ui;

import com.echec.core.Echiquier;

/**
 * Created by arthurveys on 17/06/2014.
 * Projet java ${PROJECT}
 */
public class Controler {

    Echiquier ec;
    View v;

    /**
     * Build all the program (entry point)
     */
    public Controler() {
       ec = new Echiquier();
       v = new  View(this);
    }

    /**
     * Generate the chessboard
     * @return a 2-D array of the chessboard
     */
    public String[][] getNewPlateau() {
        return ec.generateChessTab();
    }

    /**
     * Execute a chess movement
     * @param xsource the x source (pixel on app)
     * @param ysource the y source (pixel on app)
     * @param xdest the x destination (pixel on app)
     * @param ydest the y destination (pixel on app)
     * @return true if the movement is accepted
     */
    public boolean move(int xsource,int ysource,int xdest,int ydest){
        return ec.startPlay(getCoordinates(xsource),getCoordinates(ysource),getCoordinates(xdest),getCoordinates(ydest));
    }

    /**
     * Convert pixel on app coordinate to grid coordinate
     * @param x the coordinate to convert
     * @return the converted coordinate
     */
    private int getCoordinates(int x){
        return (int) Math.floor((600-x)/(600/8));
    }

    /**
     * restart the game
     */
    public void reset() {
        ec=new Echiquier();
        v.refreshviewPlateau();
    }
}

