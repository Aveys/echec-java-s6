package com.echec.ui;

import com.echec.core.Echiquier;

/**
 * Created by arthurveys on 17/06/2014.
 * Projet java ${PROJECT}
 */
public class Controler {

    Echiquier ec;
    View v;

    public Controler() {
       ec = new Echiquier();
       v = new  View(this);
    }
    public String[][] getNewPlateau() {
        return ec.generateChessTab();
    }
    public boolean move(int xsource,int ysource,int xdest,int ydest){
        return ec.startPlay(getCoordinates(xsource),getCoordinates(ysource),getCoordinates(xdest),getCoordinates(ydest));
    }
    private int getCoordinates(int x){
        return (int) Math.floor((600-x)/(600/8));
    }

    public void reset() {
        ec=new Echiquier();
        v.refreshviewPlateau();
    }
}

