package com.echec.core;

import java.util.ArrayList;

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
     * Check is the place is free
     * @param ec the chessboard to check
     * @param x the X coordinate to check
     * @param y the Y coordinate to check
     * @return true if free, false otherwise
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
			
			
			//1.
			//si la pièce est une reine, ou un fou ou tour
			if(Type_pe == (TypePiece.fou) || Type_pe == (TypePiece.reine) || Type_pe==(TypePiece.tour)){
				
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
			//2.
			//si la pièce est un cavalier
			else if(Type_pe == (TypePiece.cavalier)){
				
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
			//3.
			//si la pièce est un roi
			else if(Type_pe == (TypePiece.roi)){
				
				//variable pour savoir si le roi est en echec sur le prochain deplacmeent
				boolean echec = false;
				Jeu jeu_adverse;
				
				//vérifier si mise en echec
				//on identifie le jeu adversaire
				if(pe.getColor()==Couleur.noir){
					jeu_adverse = ec.getJeuBlanc();
				}
				else{							
					jeu_adverse = ec.getJeuNoir();
				}
				
				//pour chaque piece du jeu adverse, on verifie si il peut mettre en echec le roi
				for(Piece p : jeu_adverse.lstpiece){
					
					if(Move(p, ec, x, y)){
						//on met à jour la variable echec
						echec = true;
					}
				}
				
				if(!echec){
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
			//4.
			//si la pièce est un pion
			else if(Type_pe == (TypePiece.pion)){
				
				Pion pi=(Pion) pe;
				
				//vérification si la case n'est pas libre
				if(!checkIsFree(ec, x, y)){
					
					//récupération de la piece sur la case cible
					Piece piece_cible = ec.getPiece(x, y);
					
					//si la couleur de la pièce cible est différente de la pièce qui bouge
					if(pi.getColor() != piece_cible.getColor()){
					
						//si c'est un déplacement en diagonale 
						if((java.lang.Math.abs(y-pi.getY()) == 1) && (java.lang.Math.abs(x-pi.getX()) == 1)){
							
							//mouvement possible + déplacement de la pièce + kill de la pièce cible + incrémentation du mvt
							move = true;
							if(piece_cible.getColor()==Couleur.blanc){
								ec.getJeuBlanc().killPiece(piece_cible);
							}
							else{							
								ec.getJeuNoir().killPiece(piece_cible);
							}
							effectiveMovement(pi, x, y);
							pi.setMoves(1);
							
						}
					}
				}
				//si la case est libre
				else{
					
					//si c'est le premier mouvement du pion
					if(pi.getMoves()==0){
						
						//si le déplacement est tout droit
						if(java.lang.Math.abs(x-pi.getX()) == 0){
							
							//on autorise le déplacement + move + set move à 1
							move = true;
							effectiveMovement(pi, x, y);
							pi.setMoves(1);
							
						}
					}
					//si ce n'est pas le premier mouvement
					else{
						
						//si le déplacement est d'1 case et tout droit
						if((java.lang.Math.abs(y-pi.getY()) == 1)&&(java.lang.Math.abs(x-pi.getX()) == 0)){
							
							//on autorise le déplacement + move + set move à 1
							move = true;
							effectiveMovement(pi, x, y);
							pi.setMoves(1);
							
						}
					}
				}
			}
		}
		
		return move;
	}	
}
