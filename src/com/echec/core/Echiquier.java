package com.echec.core;

/**
 * Created by arthurveys on 16/06/2014.
 */
public class Echiquier  {

    private Jeu jeuBlanc;
    private Jeu jeuNoir;
    private int currentUser;

    /**
     * Construit un echiquier
     */
    public Echiquier() {
        this.jeuBlanc=new Jeu(Couleur.blanc);
        this.jeuNoir=new Jeu(Couleur.noir);
        this.currentUser=1; //1 -> joueur blanc, 0 -> joueur noir
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
    public void afficherEchiquier() {
        Piece tmp;
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                tmp = getPiece(j, i);
                if (tmp == null)
                    System.out.print("X(" + j + "," + i + ") |");
                else
                    System.out.print(tmp.toString() + "(" + j + "," + i + ") |");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * generate a 2-D string array of piece ("F1")
     * @return the 2-D array
     */
    public String[][] generateChessTab(){
        Piece tmp;
        String[][] res = new String[8][8];
        afficherEchiquier();

        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                tmp = getPiece(j, i);
                if (tmp == null)
                    res[j][i]="";
                else {
                    if (tmp.getColor()==Couleur.blanc)
                        res[j][i]=(tmp.name.toLowerCase());//blanc en minuscule
                    else
                        res[j][i]=(tmp.name.toUpperCase());//noir en majuscule
                }
            }
        }
        return res;
    }

    /**
     * Start the movement of the piece
     * @param xsource the X source (grid reference)
     * @param ysource the Y source (grid reference)
     * @param xdest the X destination (grid reference)
     * @param ydest the Y destination (grid reference)
     * @return if the move is accepted
     */
    public boolean startPlay(int xsource,int ysource,int xdest,int ydest){
        afficherEchiquier();// affichage console
        System.out.println("Déplacement demandée : ("+xsource+","+ysource+") -> ("+xdest+","+ydest+")");
        boolean res;
        Piece src = getPiece(xsource,ysource);// recupére la piece source du mouvement
        if (currentUser==0){// selection  selon le joueur courant
            res=jeuNoir.jouer(src,this,xdest,ydest);
            if(res) currentUser++;
        }
        else{
            res=jeuBlanc.jouer(src,this,xdest,ydest);
            if(res) currentUser--;
        }
        return res;

    }

}
