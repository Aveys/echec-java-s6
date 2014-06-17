package com.echec.core;

/**
 * Created by arthurveys on 16/06/2014.
 */
public class Echiquier  {

    private Jeu jeuBlanc;
    private Jeu jeuNoir;
    private int currentUser;

    public Echiquier() {
        this.jeuBlanc=new Jeu(Couleur.blanc);
        this.jeuNoir=new Jeu(Couleur.noir);
        this.currentUser=0;
    }

    public Jeu getJeuBlanc() {
        return jeuBlanc;
    }

    public void setJeuBlanc(Jeu jeuBlanc) {
        this.jeuBlanc = jeuBlanc;
    }

    public Jeu getJeuNoir() {
        return jeuNoir;
    }

    public void setJeuNoir(Jeu jeuNoir) {
        this.jeuNoir = jeuNoir;
    }

    public int getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(int currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * get a piece on precise coordinates (in the two players)
     * @param x X coordinate to get
     * @param y Y coordinate to get
     * @return null if no piece found on the coodinates or the piece if found
     */
    public Piece getPiece(int x, int y){
        Piece tmp=null;
        tmp=jeuBlanc.getPiece(x,y);
        if (tmp==null)
            tmp=jeuNoir.getPiece(x,y);
        return tmp;
    }

    /**
     * Display the whole chessboard
     */
    public void afficherEchiquier(){
        Piece tmp;
        for (int i = 0; i !=8; i++) {
            for (int j = 0; j!=8; j++) {
                tmp=this.getPiece(j,i);
                if(tmp==null)
                    System.out.print("X |");
                else
                    System.out.print(tmp.toString()+" |");
            }
            System.out.print("\n");
        }
    }
}
