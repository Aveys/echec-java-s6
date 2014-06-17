package com.echec.core;

/**
 * Created by arthurveys on 16/06/2014.
 */

public class Echiquier_deplacement_Utils {
    /**
     * move coordinates of the piece
     * @param pe the piece to move
     * @param x the x coordinate to move
     * @param y the y coordinate to move
     */
	public static void effectiveMovement(Piece pe,int x,int y){
        pe.setX(x);
        pe.setY(y);
	}
	
	/**
     * Check
     * @param ec
     * @param x
     * @param y
     * @return
     */
    public static boolean checkIsFree(Echiquier ec, int x,int y){
        Piece tmp=ec.getPiece(x,y);
        if (tmp == null)
            return true;
        else
            return false;
    }
	
	
	public static boolean Move(Piece pe, Echiquier ec, int x, int y){
		
		boolean move = false;
		
		//on vérifie que le mouvement est possible pour la pièce indépendemment du jeu
		if(pe.DepIsValid(x, y)){
			
			//test de type de pièce
			TypePiece Type_pe=pe.getType();
			
			//si la pièce est une reine, ou un fou
			if(Type_pe == (TypePiece.fou) || Type_pe == (TypePiece.reine) ){
				
				//vérification si la case libre
				if(checkIsFree(ec, x, y)){
					
					//déplacement de la pièce
					effectiveMovement(pe, x, y);
					move = true;
					
				}else{
					
					//récupération de la piece sur la case cible
					Piece piece_cible = ec.getPiece(x, y);
					
					//si la couleur de la pièce cible est différente de la pièce qui bouge
					if(pe.getColor() != piece_cible.getColor()){
						
						//mouvement possible + déplacement de la pièce + kill de la pièce cible
						move = true;
						if(piece_cible.getColor()==Couleur.blanc){
							ec.getJeuBlanc().killPiece(piece_cible);
						}
						else{							
							ec.getJeuNoir().killPiece(piece_cible);
						}
						effectiveMovement(pe, x, y);
					}
					
				}
			}
			
			
		}
		
		return move;
		
	}
	
	
}
