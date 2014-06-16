package com.echec.core;

public class Roi extends Piece{

	public Roi(int x, int y) {
		
		super(x, y, TypePiece.roi);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean DepIsValid(int x, int y) {
		// TODO Auto-generated method stub
		
		
		if(CoordIsValid(x, y)){
			
			
		}
		
		
		return false;
	}

}
