/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author emre
 */
public class GameWindow extends JFrame {

    
    private JPanel leftPanel;
    private JPanel rightPanel;
    
    
    private GameCellsPanel gameCellsPanel;
    private BorderLayout mainLayout;
    
    private ConnectFourGui connectFour;
    
    private boolean gameEnd = false;
    
    private JLabel user1Label;
    private JLabel user2Label;

    protected void checkWinner() {
        //System.out.println("winner: " + connectFour.getWinner());
        if (connectFour.getWinner() == 1 || connectFour.getWinner() == 2) {
            gameEnd = true;
          //  System.out.println("game end. winner: " + connectFour.getWinner());
        } else if (connectFour.getWinner() == 3) {
            gameEnd = true;
           // System.out.println("game end. draw");
        }
    }
    /** to control of users click and game is finish or not 
     * @param h height of game equals size
     * @param w width of game equals size
     */
    protected void gameCellClicked(int h, int w) {
        // System.out.println("gameCellClicked h:" + h + " w:" + w);
        if (gameEnd)
            return;
        
        boolean result = connectFour.play(w);
        if (result == true) {
            checkWinner();
            connectFour.guiUpdate();
        } else {
           // System.out.println("wrong input");
            return;
        }

        if (!gameEnd && connectFour.getMode() == ConnectFourBase.GameMode.U_vs_C) {
            // computer
            result = connectFour.play();
            if (result == true) {
                checkWinner();            
                connectFour.guiUpdate();
            } else {
               // System.out.println("program code error");
            }
        }
        
        
    }

    /** Create of game window as three part Panel
     * @param gameSize size of Game as Integer
     * @param gameMode mode of Game as User_vs_User or User_vs_Computer
     */
    public GameWindow(int gameSize, ConnectFourBase.GameMode gameMode) {
        super("ConnectFour");
        // container = getContentPane();
        
        mainLayout = new BorderLayout(gameSize, 10);
        setLayout(mainLayout);
        
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 0));
        
        
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(200, 0));
        
        
        gameCellsPanel = new GameCellsPanel(gameSize, 3, this);
        gameCellsPanel.setPreferredSize(new Dimension(600, 600));
        
        add(leftPanel, BorderLayout.WEST);
        add(gameCellsPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        
        connectFour = new ConnectFourGui(gameSize, gameMode, gameCellsPanel, this);

        
        // test
        JButton exitButton = new JButton("exit");
        GameWindow thisWindow = this;
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewGameSettingsWindow window = new NewGameSettingsWindow();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.pack();
                window.setVisible(true);
                thisWindow.dispose();
            }
        });
        //add(exitButton, BorderLayout.SOUTH);
       
        
        user1Label = new JLabel("         USER 1");
        user1Label.setPreferredSize(new Dimension(120, 120));
        user1Label.setOpaque(true);
        user1Label.setBackground(Color.BLUE);
        leftPanel.add(user1Label);
        
        user2Label = new JLabel("         USER 2");
        user2Label.setOpaque(true);
        user2Label.setBackground(Color.RED);
        user2Label.setPreferredSize(new Dimension(120, 120));
        rightPanel.add(user2Label);
        
        exitButton.setPreferredSize(new Dimension(120, 50));
        rightPanel.add(exitButton);
        
        
        // https://docs.oracle.com/javase/tutorial/uiswing/layout/none.html
        leftPanel.setLayout(null);
        Insets insets = leftPanel.getInsets();
        Dimension size = user1Label.getPreferredSize();
        user1Label.setBounds(40 + insets.left, 20 + insets.top, size.width, size.height); // soldan 40, yukardan 20 bosluk
        
        rightPanel.setLayout(null);
        insets = rightPanel.getInsets();
        size = user2Label.getPreferredSize();
        user2Label.setBounds(40 + insets.left, 20 + insets.top, size.width, size.height); // soldan 40, yukardan 20 bosluk
        
        size = exitButton.getPreferredSize();
        exitButton.setBounds(40 + insets.left, 500 + insets.top, size.width, size.height);
        
        connectFour.guiUpdate();
    }
   
    public GameWindow() {
        this(4, ConnectFourBase.GameMode.U_vs_U);
    }
    /** to determine which player will play 
     * @param userNo User1 or User2
     */
    public void setActiveUser(int userNo) {
      //  System.out.println("userNo: " + userNo);

        if (userNo == 1) {
            user1Label.setVisible(true);
            user2Label.setVisible(false);
        } else {
            user1Label.setVisible(false);
            user2Label.setVisible(true);
        }

    }
}