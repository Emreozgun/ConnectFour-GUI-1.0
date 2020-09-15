/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour;

import javax.swing.JOptionPane;

/** used that class to convert ConnectFourbase is available Gui
 *
 * @author emre
 */
public class ConnectFourGui extends ConnectFourBase {

    GameCellsPanel gameCellsPanel;
    GameWindow gameWindow;
    /**
     * @param size  game size
     * @param gameMode game mode 
     * @param gameCellsPanel  game Cells panel to gui
     * @param gameWindow game Window to starting and play 
     */
    public ConnectFourGui(int size, GameMode gameMode, GameCellsPanel gameCellsPanel, GameWindow gameWindow) {
        super(size, gameMode);
        this.gameCellsPanel = gameCellsPanel;
        this.gameWindow = gameWindow;
    }
    
    @Override
    void guiUpdate() {
        for (int i = 0 ; i < getSize() ; i++) {
            for (int j = 0 ; j < getSize() ; j++) {
                if (gameCellsPanel.getCellType(i, j) != gameCells[i][j].getType()) {
                    gameCellsPanel.setCellType(i, j, gameCells[i][j].getType());
                }
            }
        }

        gameWindow.setActiveUser(this.getUserNo());
        /**
         * if game is finish to message
         */
        if (getWinner() == 1) {
            JOptionPane.showMessageDialog(null, "User 1 Winner", "", JOptionPane.PLAIN_MESSAGE);
        } else if (getWinner() == 2) {
            JOptionPane.showMessageDialog(null, "User 2 Winner", "", JOptionPane.PLAIN_MESSAGE);
        } else if (getWinner() == 3) {
            JOptionPane.showMessageDialog(null, "Draw", "", JOptionPane.PLAIN_MESSAGE);
        }
        
    }
    
}
