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

    /**
     * Return the number of piece in game
     * @return the number of piece
     */
    public int getNBPiece(){
        return lstpiece.size();
    }
    /**
     * Return false if one or several piece are not initialised
     */
    public boolean isAllValid(){
        if (lstpiece.isEmpty())
                return false;
        for(Piece p : lstpiece){
            if (p == null)
                return false;
        }
        return true;
    }
    public Piece getPiece(int x,int y){
        if(lstpiece.isEmpty())
            return null;
        for(Piece p : lstpiece){
            if(p.getX() == x && p.getY()==y)
                return p;
        }
        return null;
    }
    public boolean killPiece(){

    }
}
