/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw8;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.ArrayList;

/** Class represent game settings size and gameMode 
 *
 * @author emre
 */
public class NewGameSettingsWindow extends JFrame {

    private final GridLayout layout;
    private final JButton startButton;
    
    private final String[] gameSizeListStrings;
    private final JComboBox gameSizeList;
    private final String[] gameModeListStrings = new String[] { "User vs User", "User vs Computer" };
    private final JComboBox gameModeList;

    private void startNewGame(int gameSize, ConnectFourBase.GameMode gameMode) {
        GameWindow gameWindow = new GameWindow(gameSize, gameMode);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.pack();
        gameWindow.setVisible(true); // display frame   
    }
    
   /**
    * First Window of game to choose size and game Mode 
    */ 
   public NewGameSettingsWindow() {
        super("New Game");
        layout = new GridLayout(3, 2, 5, 5);
        setLayout(layout);

        startButton = new JButton("start");
        startButton.addActionListener((ActionEvent e) -> {
            startButtonClick();
        });

        // create 4 to 29 game board size list
        gameSizeListStrings = new String[29 - 4 + 1];
        for (int i = 4; i <= 29; i++) {
            gameSizeListStrings[i - 4] = "" + i;
        }
        gameSizeList = new JComboBox(gameSizeListStrings);
        gameSizeList.setSelectedIndex(8-4);
        
        
        gameModeList = new JComboBox(gameModeListStrings);

        add(new JLabel("Game Size"));
        add(gameSizeList); // add game size list
        
        
        add(new JLabel("Game Mode"));
        add(gameModeList);
        

        add(new JLabel(""));
        add(startButton); // add start button

    }
    /**
     * To start the game with start button as Gamesize and GameMode 
     */
    private void startButtonClick() {
        int gameSize = Integer.parseInt(gameSizeListStrings[gameSizeList.getSelectedIndex()]);
        ConnectFourBase.GameMode gameMode;
        if (gameModeList.getSelectedIndex() == 0)
            gameMode = ConnectFourBase.GameMode.U_vs_U;
        else
            gameMode = ConnectFourBase.GameMode.U_vs_C;
        startNewGame(gameSize, gameMode);
        setVisible(false);
    }

}
