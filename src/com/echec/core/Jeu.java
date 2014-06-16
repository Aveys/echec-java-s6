package com.echec.core;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.ArrayList;

/**
 * Created by arthurveys on 16/06/2014.
 */
public class Jeu {

    /**
     * list of all pieces for the player
     */
    public ArrayList<Piece> lstpiece;
    Couleur color;

    /**
     * Build a game of a player
     */
    public Jeu(Couleur c) {
        this.lstpiece = new ArrayList<Piece>();
        this.color=c;
        this.init();
    }

    /**
     * Initilize the game
     */
    private void init(){
        if(this.color==Couleur.noir) {
            for (int i = 0; i != 7; i++) {
                lstpiece.add(new Pion(i,6 ));
            }
            lstpiece.add(new Tour(0,7));
            lstpiece.add(new Cavalier(1,7));
            lstpiece.add(new Fou(2,7));
            lstpiece.add(new Roi(3,7));
            lstpiece.add(new Reine(4,7));
            lstpiece.add(new Fou(5,7));
            lstpiece.add(new Cavalier(6,7));
            lstpiece.add(new Tour(7,7));
        }
        else{
            for (int i = 0; i != 7; i++) {
                lstpiece.add(new Pion(i,1 ));
            }
            lstpiece.add(new Tour(0,0));
            lstpiece.add(new Cavalier(1,0));
            lstpiece.add(new Fou(2,0));
            lstpiece.add(new Roi(3,0));
            lstpiece.add(new Reine(4,0));
            lstpiece.add(new Fou(5,0));
            lstpiece.add(new Cavalier(6,0));
            lstpiece.add(new Tour(7,0));
        }
    }

    /**
     * Return the number of piece in game for this player
     * @return the number of piece
     */
    public int getNBPiece(){
        return lstpiece.size();
    }
    /**
     * Check is all pieces was corrctly set up
     * @return false if one or several piece are not initialised
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

    /**
     * Return a piece object for coordinates in entry
     * @param x The x coordinate
     * @param y The y coordinate
     * @return the piece or null if not find
     */
    public Piece getPiece(int x,int y){
        if(lstpiece.isEmpty())
            return null;
        for(Piece p : lstpiece){
            if(p.getX() == x && p.getY()==y)
                return p;
        }
        return null;
    }

    /**
     * Kill a piece of the player
     * @param p The piece to kill
     * @return true if everything worked
     */
    public boolean killPiece(Piece p){
        return killPiece(p.getX(),p.getY());
    }

    /**
     * Kill a piece of the player
     * @param x the x coord of the piece
     * @param y the y coord of the piece
     * @return true if everything worked
     */
    public boolean killPiece(int x, int y){
        return lstpiece.remove(this.getPiece(x,y));
    }

}
