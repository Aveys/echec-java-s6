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
	
	/**
	 * move a piece on the chessboard
	 * @param pe the piece to move
	 * @param ec the chessboard you play on
	 * @param x the X coordinate where you want to move the piece
	 * @param y the Y coordinate where you want to move the piece
	 * @return true if the piece has moved, false otherwise
	 */
	public static boolean Move(Piece pe, Echiquier ec, int x, int y){
		
		//variable de retour
		boolean move = false;
		
		//on vérifie que le mouvement ne soit pas du sur place
		if(pe.getX()-x == 0 && pe.getY()-y==0){
			
			return move;
		}
		
		//on vérifie que la pièce selecetionnée soit de la meme couleur que le joueur entrain de jouer
        if(pe.getColor()==Couleur.blanc){
            if (ec.getCurrentUser()==0)
                return move;
        }
        if(pe.getColor()==Couleur.noir){
            if (ec.getCurrentUser()==1)
                return move;
        }
      //on vï¿½rifie que le mouvement est possible pour la piï¿½ce indï¿½pendemment du jeu
		if(pe.DepIsValid(x, y)){
			
			//test de type de piï¿½ce
			TypePiece Type_pe=pe.getType();
			
			
			//1.
			//si la piï¿½ce est une reine, ou un fou ou tour
			if(Type_pe == (TypePiece.fou) || Type_pe == (TypePiece.reine) || Type_pe==(TypePiece.tour)){
				
				move = MoveBasic(pe, ec, x, y);
			}
			//2.
			//si la piï¿½ce est un cavalier
			else if(Type_pe == (TypePiece.cavalier)){

                move = MoveCav(pe, ec, x, y);
			}
			//3.
			//si la piï¿½ce est un roi
			else if(Type_pe == (TypePiece.roi)){

                move = MoveKing(pe, ec, x, y);
			}
			//4.
			//si la piï¿½ce est un pion
			else if(Type_pe == (TypePiece.pion)){

                move = MovePion(pe, ec, x, y);
			}
		}
		
		return move;
	}
	
	/**
	 * move a queen,bishop, or a rook
	 * @param pe the piece you want to move
	 * @param ec the chessboard you play on
	 * @param x the X coordinate where you want to move the piece
	 * @param y the Y coordinate where you want to move the piece
	 * @return true if the piece has moved, false otherwise
	 */
	//mouvement pour reine, fou, tour
	public static boolean MoveBasic(Piece pe, Echiquier ec, int x, int y){
		boolean move = false;
		
		//vï¿½rification si la case libre
		if(checkIsFree(ec, x, y)){
			
			//dï¿½placement de la piï¿½ce
			effectiveMovement(pe, x, y);
			move = true;
			
		}else{
			
			//rï¿½cupï¿½ration de la piece sur la case cible
			Piece piece_cible = ec.getPiece(x, y);
			
			//si la couleur de la piï¿½ce cible est diffï¿½rente de la piï¿½ce qui bouge
			if(pe.getColor() != piece_cible.getColor()){
				
				//mouvement possible + dï¿½placement de la piï¿½ce + kill de la piï¿½ce cible
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
		
		return move;
		
	}
	/**
	 * move a knight
	 * @param pe the piece you want to move
	 * @param ec the chessboard you play on
	 * @param x the X coordinate where you want to move the piece
	 * @param y the Y coordinate where you want to move the piece
	 * @return true if the piece has moved, false otherwise
	 */
	//mouvement pour cavalier
		public static boolean MoveCav(Piece pe, Echiquier ec, int x, int y){
			boolean move = false;
			
			//vï¿½rification si la case libre
			if(checkIsFree(ec, x, y)){
				
				//dï¿½placement de la piï¿½ce
				effectiveMovement(pe, x, y);
				move = true;
				
			}else{
				
				//rï¿½cupï¿½ration de la piece sur la case cible
				Piece piece_cible = ec.getPiece(x, y);
				
				//si la couleur de la piï¿½ce cible est diffï¿½rente de la piï¿½ce qui bouge
				if(pe.getColor() != piece_cible.getColor()){
					
					//mouvement possible + dï¿½placement de la piï¿½ce + kill de la piï¿½ce cible
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
			
			return move;
	}
		/**
		 * move a king
		 * @param pe the piece you want to move
		 * @param ec the chessboard you play on
		 * @param x the X coordinate where you want to move the piece
		 * @param y the Y coordinate where you want to move the piece
		 * @return true if the piece has moved, false otherwise
		 */
	//mouvement pour roi
	public static boolean MoveKing(Piece pe, Echiquier ec, int x, int y){
		boolean move = false;
		
		//variable pour savoir si le roi est en echec sur le prochain deplacmeent
		boolean echec = false;
		Jeu jeu_adverse;
		
		//vï¿½rifier si mise en echec
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
				//on met ï¿½ jour la variable echec
				echec = true;
			}
		}
		
		if(!echec){
			//vï¿½rification si la case libre
			if(checkIsFree(ec, x, y)){
				
				//dï¿½placement de la piï¿½ce
				effectiveMovement(pe, x, y);
				move = true;
				
			}else{
				
				//rï¿½cupï¿½ration de la piece sur la case cible
				Piece piece_cible = ec.getPiece(x, y);
				
				//si la couleur de la piï¿½ce cible est diffï¿½rente de la piï¿½ce qui bouge
				if(pe.getColor() != piece_cible.getColor()){
					
					//mouvement possible + dï¿½placement de la piï¿½ce + kill de la piï¿½ce cible
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
		
		return move;
	}
	/**
	 * move a pawn
	 * @param pe the piece you want to move
	 * @param ec the chessboard you play on
	 * @param x the X coordinate where you want to move the piece
	 * @param y the Y coordinate where you want to move the piece
	 * @return true if the piece has moved, false otherwise
	 */
	//mouvement pour pion
	public static boolean MovePion(Piece pe, Echiquier ec, int x, int y){
		boolean move = false;
			
		Pion pi=(Pion) pe;
		
		//vï¿½rification si la case n'est pas libre
		if(!checkIsFree(ec, x, y)){
			
			//rï¿½cupï¿½ration de la piece sur la case cible
			Piece piece_cible = ec.getPiece(x, y);
			
			//si la couleur de la piï¿½ce cible est diffï¿½rente de la piï¿½ce qui bouge
			if(pi.getColor() != piece_cible.getColor()){
			
				//si c'est un dï¿½placement en diagonale 
				if((java.lang.Math.abs(y-pi.getY()) == 1) && (java.lang.Math.abs(x-pi.getX()) == 1)){
					
					//mouvement possible + dï¿½placement de la piï¿½ce + kill de la piï¿½ce cible + incrï¿½mentation du mvt
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
				
				//si le dï¿½placement est tout droit
				if(java.lang.Math.abs(x-pi.getX()) == 0){
					
					//on autorise le dï¿½placement + move + set move ï¿½ 1
					move = true;
					effectiveMovement(pi, x, y);
					pi.setMoves(1);
					
				}
			}
			//si ce n'est pas le premier mouvement
			else{
				
				//si le dï¿½placement est d'1 case et tout droit
				if((java.lang.Math.abs(y-pi.getY()) == 1)&&(java.lang.Math.abs(x-pi.getX()) == 0)){
					
					//on autorise le dï¿½placement + move + set move ï¿½ 1
					move = true;
					effectiveMovement(pi, x, y);
					pi.setMoves(1);
					
				}
			}
		}
		
		return move;
	}
}
