package com.echec.ui;

import com.echec.core.Echiquier;

/**
 * Created by arthurveys on 17/06/2014.
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
        return ec.startPlay(xsource,ysource,xdest,ydest);
    }
}

