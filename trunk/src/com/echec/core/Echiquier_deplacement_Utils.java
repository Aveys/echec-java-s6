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
		
		//on v�rifie que le mouvement est possible pour la pi�ce ind�pendemment du jeu
		if(pe.DepIsValid(x, y)){
			
			//test de type de pi�ce
			TypePiece Type_pe=pe.getType();
			
			//si la pi�ce est une reine, ou un fou
			if(Type_pe == (TypePiece.fou) || Type_pe == (TypePiece.reine) ){
				
				//v�rification si la case libre
				if(checkIsFree(ec, x, y)){
					
					//d�placement de la pi�ce
					effectiveMovement(pe, x, y);
					move = true;
					
				}else{
					
					//r�cup�ration de la piece sur la case cible
					Piece piece_cible = ec.getPiece(x, y);
					
					//si la couleur de la pi�ce cible est diff�rente de la pi�ce qui bouge
					if(pe.getColor() != piece_cible.getColor()){
						
						//mouvement possible + d�placement de la pi�ce + kill de la pi�ce cible
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
