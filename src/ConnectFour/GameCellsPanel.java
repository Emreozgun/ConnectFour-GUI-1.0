/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/** Class represents game Cells panel as Button 
 * @author emre
 */
public class GameCellsPanel extends JPanel {

    private final JButton gameCellButtons[][];
    private final GridLayout gameCellsLayout;
    
    private final int gameSize;
    private final int gapSize;
    
    /**
     * @param h height of game equals size
     * @param w width of game equals size
     * @param user to set user's color
     */
    public void setCellType(int h, int w, ConnectFourBase.CellType user) {
        
        if (user == ConnectFourBase.CellType.EMPTY)
            gameCellButtons[h][w].setBackground(Color.GRAY);
        else if (user == ConnectFourBase.CellType.USER_1)
            gameCellButtons[h][w].setBackground(Color.BLUE);
        else if (user == ConnectFourBase.CellType.USER_2)
            gameCellButtons[h][w].setBackground(Color.RED);
        
    }
    /**
     * @param h height of game equals size 
     * @param w width of game equals size
     * @return cellType (USER_1, USER_2, EMPTY)
     */
    public ConnectFourBase.CellType getCellType(int h, int w) {
        if (gameCellButtons[h][w].getBackground() == Color.BLUE)
            return ConnectFourBase.CellType.USER_1;
        
        if (gameCellButtons[h][w].getBackground() == Color.RED)
            return ConnectFourBase.CellType.USER_2;
        
        return ConnectFourBase.CellType.EMPTY;
    }
    /**
     * @param gameSize Game size as Integer
     * @param gapSize  space of every cell
     * @param gameWindow window of playing the game 
     */
    public GameCellsPanel(int gameSize, int gapSize, GameWindow gameWindow) {
        this.gameSize = gameSize;
        this.gapSize = gameSize;
        gameCellsLayout = new GridLayout(gameSize, gameSize, gapSize, gapSize);
        setLayout(gameCellsLayout);
        gameCellButtons = new JButton[gameSize][gameSize];
        

        for (int h = 0; h < gameSize; h++) {
            for (int w = 0; w < gameSize; w++) {
                
                final int hFinal = h;
                final int wFinal = w;
                gameCellButtons[h][w] = new JButton("");
                gameCellButtons[h][w].addActionListener((ActionEvent e) -> {
                    gameWindow.gameCellClicked(hFinal, wFinal);
                });
                
                add(gameCellButtons[h][w]);
            }
        }
    }
    

}
