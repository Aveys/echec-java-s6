package com.echec.ui;

import com.echec.core.Couleur;
import com.echec.core.Echiquier;
import com.echec.core.Piece;
import com.echec.core.TypePiece;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chessgame extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    Echiquier ec;

    public Chessgame(){
        Dimension boardSize = new Dimension(600, 600);
        ec = new Echiquier();

        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane

        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.blue : Color.white );
            else
                square.setBackground( i % 2 == 0 ? Color.white : Color.blue );
        }

        //Add a few pieces to the board
        Piece tmp;
        JPanel panel;
        int count =0;
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                tmp=ec.getPiece(j,i);
                if(tmp!=null) {
                    panel = (JPanel) chessBoard.getComponent(count);
                    panel.add(getImage(tmp));
                }
                count++;
            }
        }
    }

    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    //Move the chess piece around

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    //Drop the chess piece back onto the chess board

    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;

        chessPiece.setVisible(false);
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JLabel){
            Container parent = c.getParent();
            parent.remove(0);
            parent.add( chessPiece );
        }
        else {
            Container parent = (Container)c;
            parent.add( chessPiece );
        }

        chessPiece.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e) {

    }
    public JLabel getImage(Piece p){
        if(p.getType()== TypePiece.cavalier){
            if(p.getColor()== Couleur.blanc){
                return new JLabel(new ImageIcon("resources/cavalierBlancS.png"));
            }
            else{
                return new JLabel(new ImageIcon("resources/cavalierNoirS.png"));
            }
        }
        else if(p.getType()==TypePiece.fou){
            if(p.getColor()==Couleur.blanc){
                return new JLabel(new ImageIcon("resources/fouBlancS.png"));
            }
            else{
                return new JLabel(new ImageIcon("resources/fouNoirS.png"));
            }
        }
        else if(p.getType()==TypePiece.pion){
            if(p.getColor()==Couleur.blanc){
                return new JLabel(new ImageIcon("resources/pionBlancS.png"));
            }
            else{
                return new JLabel(new ImageIcon("resources/pionNoirS.png"));
            }
        }
        else if(p.getType()==TypePiece.tour){
            if(p.getColor()==Couleur.blanc){
                return new JLabel(new ImageIcon("resources/tourBlancS.png"));
            }
            else{
                return new JLabel(new ImageIcon("resources/tourNoireS.png"));
            }
        }
        else if(p.getType()==TypePiece.roi){
            if(p.getColor()==Couleur.blanc){
                return new JLabel(new ImageIcon("resources/roiBlancS.png"));
            }
            else{
                return new JLabel(new ImageIcon("resources/roiNoirS.png"));
            }
        }
        else if(p.getType()==TypePiece.reine){
            if(p.getColor()==Couleur.blanc){
                return new JLabel(new ImageIcon("resources/reineBlancS.png"));
            }
            else{
                return new JLabel(new ImageIcon("resources/reineNoireS.png"));
            }
        }
        else
            return null;
    }
    public static void main(String[] args) {
        JFrame frame = new Chessgame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }
}