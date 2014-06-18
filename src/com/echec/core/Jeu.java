package com.echec.core;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by arthurveys on 16/06/2014.
 * Projet java
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
    public boolean jouer(Piece source,Echiquier ec,int xdest,int ydest){
        boolean execution=false;
        if (Echiquier_deplacement_Utils.Move(source, ec, xdest, ydest)) {
            System.out.println("Mouvement validé !");
            execution = true;
        } else
            System.out.println("Deplacement impossible");
        return execution;
    }
    public boolean jouerConsole(Echiquier ec) {
        boolean execution=false;
        String inputValue;
        System.out.println("C'est au tour du joueur "+color);
        Piece source;
        int xdest;
        int ydest;

        while(!execution){
            boolean exec_source=false;
            do{
                inputValue="";
                while (inputValue != "quit" && !checkValue(inputValue)) {
                    //affichage d'un curseur
                    System.out.print("Piece à bouger>");
                    //Création d'un flux de lecteur sur l'entrée standard System.in
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    //Lecture bloquante de l'entrée standard, retour la valeur entrée par l'utilisateur
                    try {
                        inputValue = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                if (inputValue == "quit")
                    return false;
                else {
                    String[] tmp = inputValue.split(",");
                    source = getPiece(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));

                    if(source!=null)
                        exec_source=true;
                    else
                        System.out.println("Cette piece n'existe pas");
                }
            }while(!exec_source);
            System.out.println("Piece séléctionnée :"+source);
            boolean exec_dest=false;
            do {
                inputValue="";
                while (!inputValue.equals("quit") && !checkValue(inputValue)) {
                    //affichage d'un curseur
                    System.out.print("Destination>");
                    //Création d'un flux de lecteur sur l'entrée standard System.in
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    //Lecture bloquante de l'entrée standard, retour la valeur entrée par l'utilisateur
                    try {
                        inputValue = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (inputValue == "quit")
                    return false;
                else {
                    String[] tmp = inputValue.split(",");
                    xdest = Integer.parseInt(tmp[0]);
                    ydest = Integer.parseInt(tmp[1]);
                    if(xdest>0 && ydest>0)
                        exec_dest=true;
                    else
                        System.out.println("coordonnées invalides");
                }
            }while(!exec_dest);
            if (Echiquier_deplacement_Utils.Move(source, ec, xdest, ydest)) {
                System.out.println("Mouvement validé !");
                execution = true;
            } else
                System.out.println("Deplacement impossible");

        }
        return true;
    }
    /**
     * Vérifie si l'entrée est correcte ou non
     * @param inputValue valeur entrée par l'utilisateur
     * @return true valeur trouvée, false non trouvée
     */
    private static boolean checkValue(String inputValue) {
        try {
            //le cast de la valeur string est indispensable pour la comparée à l'entier
            if (inputValue.matches("^[1-8],[1-8]$")) {

                return true;
            }
        } catch (Exception e) {
            //si la valeur entrée par l'utilisateur n'est pas une entier exemple "12"
            //retourne un message d'erreur
            System.out.println("Valeur incorrecte (coordonnées de type X,X)");
        }
        return false;
    }

    /**
     * Initialize the game
     */
    private void init(){
        if(color==Couleur.noir) {
            for (int i = 0; i != 8; i++) {
                lstpiece.add(new Pion(i,6,Couleur.noir,"P"+i));
            }
            lstpiece.add(new Tour(0,7,Couleur.noir,"T1"));
            lstpiece.add(new Cavalier(1,7,Couleur.noir,"C1"));
            lstpiece.add(new Fou(2,7,Couleur.noir,"F1"));
            lstpiece.add(new Roi(3,7,Couleur.noir,"Ro"));
            lstpiece.add(new Reine(4,7,Couleur.noir,"Re"));
            lstpiece.add(new Fou(5,7,Couleur.noir,"F2"));
            lstpiece.add(new Cavalier(6,7,Couleur.noir,"C2"));
            lstpiece.add(new Tour(7,7,Couleur.noir,"T2"));
        }
        else{
            for (int i = 0; i != 8; i++) {
                lstpiece.add(new Pion(i,1,Couleur.blanc,"P"+i ));
            }
            lstpiece.add(new Tour(0,0,Couleur.blanc,"T1"));
            lstpiece.add(new Cavalier(1,0,Couleur.blanc,"C1"));
            lstpiece.add(new Fou(2,0,Couleur.blanc,"F1"));
            lstpiece.add(new Roi(3,0,Couleur.blanc,"Ro"));
            lstpiece.add(new Reine(4,0,Couleur.blanc,"Re"));
            lstpiece.add(new Fou(5,0,Couleur.blanc,"F2"));
            lstpiece.add(new Cavalier(6,0,Couleur.blanc,"C2"));
            lstpiece.add(new Tour(7,0,Couleur.blanc,"T2"));
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
        boolean valid = true;
        if (lstpiece.isEmpty())
            valid=false;
        for(Piece p : lstpiece){
            if (p == null)
                valid=false;
        }
        return valid;
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
        else {
            Piece trouve=null;
            for (Piece p : lstpiece) {
                if (p.getX() == x && p.getY() == y)
                    trouve=p;
            }
            if (trouve!=null)
                return trouve;
            else
                return null;
        }
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
        return lstpiece.remove(getPiece(x, y));
    }

}
