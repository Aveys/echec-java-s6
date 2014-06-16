package com.echec.core;

public class Pion extends Piece{

	int moves = 0;
	
	public Pion(int x, int y) {
		super(x, y, TypePiece.pion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean DepIsValid(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
