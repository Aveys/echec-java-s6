package com.echec.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View extends JFrame implements MouseListener, MouseMotionListener {

    String[][] plateau;
    Controler control;
    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private int xPressed;
    private int yPressed;


    public View(Controler cont){
        this.control=cont;
        Dimension boardSize = new Dimension(600, 600);
        plateau = new String[8][8];

        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);


        //Add a chess board to the Layechar plateaured Pane

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
        refreshviewPlateau();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);


    }
    public void refreshviewPlateau(){
        plateau=control.getNewPlateau();
        JPanel panel;
        //Add a few pieces to the board
        String tmp;
        int count =0;
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                tmp=plateau[j][i];
                if(tmp!="") {
                    try {
                        panel = (JPanel) chessBoard.getComponent(count);
                        panel.add(getImage(tmp));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
        xPressed=(600-e.getX())/(600/8);
        yPressed=(600-e.getX())/(600/8);
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
    public JLabel getImage(String s) {
        if (s == "")
            return null;
        if (s.matches("c[0-8]"))
            return new JLabel(new ImageIcon("resources/cavalierBlancS.png"));
        else if (s.matches("C[0-8]"))
            return new JLabel(new ImageIcon("resources/cavalierNoirS.png"));
        else if (s.matches("f[0-8]"))
            return new JLabel(new ImageIcon("resources/fouBlancS.png"));
        else if (s.matches("F[0-8]"))
            return new JLabel(new ImageIcon("resources/fouNoirS.png"));
        else if (s.matches("p[0-8]"))
            return new JLabel(new ImageIcon("resources/pionBlancS.png"));
        else if (s.matches("P[0-8]"))
            return new JLabel(new ImageIcon("resources/pionNoirS.png"));
        else if (s.matches("t[0-8]"))
            return new JLabel(new ImageIcon("resources/tourBlancS.png"));
        else if (s.matches("T[0-8]"))
            return new JLabel(new ImageIcon("resources/tourNoireS.png"));
        else if (s.matches("ro"))
            return new JLabel(new ImageIcon("resources/roiBlancS.png"));
        else if (s.matches("RO"))
            return new JLabel(new ImageIcon("resources/roiNoirS.png"));
        else if (s.matches("re"))
            return new JLabel(new ImageIcon("resources/reineBlancS.png"));
        else if (s.matches("RE"))
            return new JLabel(new ImageIcon("resources/reineNoireS.png"));
        else
            return null;
    }
}