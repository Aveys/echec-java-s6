package com.echec.core;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by arthurveys on 16/06/2014.
 */
public class Jeu {

    public ArrayList<Piece> lstpiece;

    public Jeu() {
        this.lstpiece = new ArrayList<Piece>();
        this.init();
    }

    private boolean init() throws NotImplementedException{
        return false;
    }
    
}
