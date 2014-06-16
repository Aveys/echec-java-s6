package com.echec.core;


/**
 * Created by arthurveys on 16/06/2014.
 */
public abstract class Piece {

    private int x;
    private int y;
    private Couleur color;
    private TypePiece type;


    //constructeur
    public Piece(int x, int y, TypePiece type) {
        super();
        this.x = x;
        this.y = y;
        this.type = type;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Couleur getColor() {
        return color;
    }

    public void setColor(Couleur color) {
        this.color = color;
    }

    public TypePiece getType() {
        return type;
    }

    public void setType(TypePiece type) {
        this.type = type;
    }


    //teste la validitÈ de 2 coordonnÈes
    public static boolean CoordIsValid(int x, int y){

        if(x>8 || x<0){

            return false;
        }
        if(y>8 || y<0){

            return false;
        }

        return true;

    }

    //methode abstraite vÈrifiant si le dÈplacement est possible
    public abstract boolean DepIsValid();
}
