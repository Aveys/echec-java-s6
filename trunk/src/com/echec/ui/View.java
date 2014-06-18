package com.echec.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame implements MouseListener, MouseMotionListener {

    private final Dimension boardSize;
    String[][] plateau;
    Controler control;
    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private int xPressed;
    private int yPressed;
    private JMenuBar jm_bar;
    private JMenu jm_file;
    private JMenuItem jmi_new;

    /**
     * Build a view
     * @param cont the controller
     */
    public View(Controler cont){
        jm_bar=new JMenuBar();
        jm_file = new JMenu("Fichier");
        jmi_new = new JMenuItem("Nouvelle partie");


        this.control=cont;
        boardSize = new Dimension(600, 600);
        plateau = new String[8][8];

        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        jmi_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.reset();
            }
        });

        jm_bar.add(jm_file);
        jm_file.add(jmi_new);
        this.setJMenuBar(jm_bar);


        //Add a chess board to the Layechar plateaured Pane

        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {//build the grid
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.blue : Color.white );
            else
                square.setBackground( i % 2 == 0 ? Color.white : Color.blue );
        }

        refreshviewPlateau();//add the piece

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /**
     * Refresh the piece position
     */
    public void refreshviewPlateau(){
        layeredPane.setVisible(false);
        layeredPane.removeAll();
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {//build the grid
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.blue : Color.white );
            else
                square.setBackground( i % 2 == 0 ? Color.white : Color.blue );
        }

        plateau=control.getNewPlateau();//récupére les pieces de l'échiquier
        JPanel panel;
        //Add a few pieces to the board
        String tmp;
        int count =0;
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                tmp=plateau[j][i];
                if(!tmp.equals("")) {
                    try {
                        panel = (JPanel) chessBoard.getComponent(count);//place les pieces dans le JPanel
                        panel.add(getImage(tmp));//récupére l'image associée
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                count++;
            }
        }
        layeredPane.setVisible(true);
    }

    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        xPressed=e.getX();//sauvegardes du x et y du lors du clic
        yPressed=e.getY();
        chessPiece = (JLabel)c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    //Move the chess piece around

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);// mouvement de la piece lors du drag
    }

    //Drop the chess piece back onto the chess board

    public void mouseReleased(MouseEvent e) {
        if(e.getX()>600 || e.getX()<0||e.getY()>600 || e.getY()<0){//remise en place si la piece est sortie de l'échiquier
            if (chessPiece == null) return;

            chessPiece.setVisible(false);
            Component c = chessBoard.findComponentAt(xPressed, yPressed);

            if (c instanceof JLabel) {
                Container parent = c.getParent();
                parent.remove(0);
                parent.add(chessPiece);
            } else {
                Container parent = (Container) c;
                parent.add(chessPiece);
            }

            chessPiece.setVisible(true);
        }
        else {
            if (control.move(xPressed,yPressed,e.getX(),e.getY())) {//Vérifie la validité du déplacement
                if (chessPiece == null) return;

                chessPiece.setVisible(false);
                Component c = chessBoard.findComponentAt(e.getX(), e.getY());

                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    parent.add(chessPiece);
                } else {
                    Container parent = (Container) c;
                    parent.add(chessPiece);
                }

                chessPiece.setVisible(true);
            }
            else{// si le déplacement est non valide -> La piece retourne à ça place
                if (chessPiece == null) return;

                chessPiece.setVisible(false);
                Component c = chessBoard.findComponentAt(xPressed, yPressed);

                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    parent.add(chessPiece);
                } else {
                    Container parent = (Container) c;
                    parent.add(chessPiece);
                }

                chessPiece.setVisible(true);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Get image for a piece
     * @param s The piece
     * @return ImagaIcon of the piece
     */
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