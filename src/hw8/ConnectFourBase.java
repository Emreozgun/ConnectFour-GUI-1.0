/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw8;

import java.util.Random;

/**
 *Abstract Base Class of Game
 * @author emre
 */
public abstract class ConnectFourBase {
    /**
     * Game have two type: USER_vs_USER and USER_vs_COMPUTER
     */
    public enum GameMode {
        U_vs_U, U_vs_C
    };
    /**
     * Cell have three type:Firstly every cell is EMPTY,after move cells can be USER_1 or USER_2
     */
    public enum CellType {
        EMPTY, USER_1, USER_2
    };

    protected final int size;
    protected final GameMode gameMode;
    protected final Cell[][] gameCells;

    protected int turn;
    protected int winner;
    
    abstract void guiUpdate();
    
    /**
     * Inner class represent a Cell
     */
    protected class Cell {
        private int position; // w
        private int rowNum; // h
        private CellType type;
        
        public Cell() {
            this.position = 0;
            this.rowNum = 0;
            this.type = CellType.EMPTY;
        }
        /** Creates an employee with the specified name.
        * @param position The Cell’s position.
        * @param rowNum The Cell’s row number.
        * @param type The Cell’s type.
        */
        public Cell(int position, int rowNum, CellType type){
            this.position = position;
            this.rowNum = rowNum;
            this.type = type;
        }
        /** Gets the cell’s position.
        * @return A int representing the cell’s position
        */
        public int getPosition() { return position; }
        /** Sets the cell’s position.
         * @param position A int containing the cell’s
         *     position
        */
        public void setPosition( int position ){
            this.position = position;
        }
        /** Sets the cell’s position.
         * @param rowNum A int containing the cell’s
         *     row
        */
        public void setRow( int rowNum ){
            this.rowNum = rowNum;
        }
        /** Gets the cell’s row Number.
        * @return A int representing the cell’s row number
        */
        public int getRow() { return rowNum; }
        /** Gets the cell’s type.
        * @return A Celltype representing the cell’s type.
        */
        public CellType getType()  { return type; }
        /** Sets the cell’s type.
         * @param type A Celltype containing the cell’s type
        */
        public void setType(CellType type){
            this.type = type;
        }
    };
    /** Creates an employee with the specified name.
    * @param size The game size.
    * @param gameMode  The game Mode
    */
    public ConnectFourBase(int size, GameMode gameMode) {
        this.turn = 0;
        this.size = size;
        this.gameMode = gameMode;
        this.gameCells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
               //gameCells[i][j] = CellType.EMPTY;
               gameCells[i][j] = new Cell(j, i, CellType.EMPTY);
            }
        }
    }
    
    /**
     * 
     * @return winner as O or 1
     */
    public int getWinner() {
        return winner;
    }
    /**
     * 
     * @return game size 
     */
    public int getSize() {
        return size;
    }
    /**
     * 
     * @return gameMode as User_vs_User or User_vs_Computer
     */
    public GameMode getMode() {
        return gameMode;
    }
    
    /** That play function play as computer
     * 
     * @return true if computer move is success
     */
    public boolean play() {
        int input = getComputerInput();

        boolean moveReturn = play(input);

        return moveReturn;
    }

    /**  That play function play as user
     * @param pos position of cell
     * @return true if user move is success
     */
    public boolean play(int pos) {
        if (winner != 0)
            return false;

        boolean result = moveUser(pos, getUserCelltype());
        winner = checkWinner();
        if (winner == 0 && result == true)
            turn++;
        return result;
    }

    /**
     * 
     * @return 0: no winner, 1: user 1 win, 2: user 2 win, 3: draw
     */
    private int checkWinner() {
        // check user 1
        int result = checkWinner(CellType.USER_1);
        if (result == 3)
            return 3; // draw
        if (result == 1)
            return 1; // user 1 winner
        
        // check user 2
        result = checkWinner(CellType.USER_2);
        if (result == 2)
            return 2; // user 2 winner
        
        return 0; // no winner
    }
    /**it used play and getComputerInput functions
     * 
     * @param w weight of game,equal size of game
     * @param cellType Cell type of that Cell
     * @return true if cell is avaliable to move 
     */
    private boolean moveUser(int w, CellType cellType)
    {
        int h;
        //to put in the empty place
        for (h = getSize() - 1; h >= 0; h--) {
            if (gameCells[h][w].getType() == CellType.EMPTY) {
                gameCells[h][w].setType(cellType);
                return true;
            }
        }
        
        return false;
    }

    /** Move like user and then remove that move 
     * @param w weight
     * @return true if remove can be available
     */
    private boolean removeTop(int w) {

        int h;
        for (h = 0; h < getSize(); h++) {
            if (gameCells[h][w].getType() != CellType.EMPTY) {
                break;
            }
        }
        if (h >= size) {
            return false;
        }

        gameCells[h][w].setType(CellType.EMPTY);
        return true;
    }
    /**
     * @return Cell position of the computer
     */
    private int getComputerInput() {
        int rivalUserNo;
        //Computer will play like user
        CellType rivalCelltype;

        if (getUserNo() == 1) {
            rivalUserNo = 2;
            rivalCelltype = CellType.USER_2;
        } else {
            rivalUserNo = 1;
            rivalCelltype = CellType.USER_1;
        }

        int w;

        //İt the first precedence(oncelik)
        // to look for himself can be winner
        for (w = 0; w < size; w++) {
            if (moveUser(w, getUserCelltype()) == false) {
                continue;
            }
            if (checkWinner(getUserCelltype()) != 0) {
                removeTop(w);
                return w;
            }

            removeTop(w);
        }

        //İt the second precedence(
        // to look for rival can be winner
        for (w = 0; w < size; w++) {
            if (moveUser(w, rivalCelltype) == false) {
                continue;
            }

            if (checkWinner(rivalCelltype) != 0) {
                removeTop(w);
                return w;
            }

            removeTop(w);
        }

        //Last precedence to randomly
        Random rand = new Random();
        return rand.nextInt(size);
    }
    /**
     * @return user no which user will be play
     */
    protected int getUserNo() {
        return (turn % 2) + 1;
    }
    /**
     * @return Cell type as getUserNo() function == userNo
     */
    protected CellType getUserCelltype() {
        if (getUserNo() == 1) {
            return CellType.USER_1;
        } else {
            return CellType.USER_2;
        }
    }


    private boolean checkHorizonal(CellType user) {
        for (int h = 0; h < size; h++) {
            for (int w = 3; w < size; w++) {
                
                if (gameCells[h][w].getType() == user && gameCells[h][w - 1].getType() == user
                        && gameCells[h][w - 2].getType() == user && gameCells[h][w - 3].getType() == user) {
                    return true;
                }
            }
        }
        return false;
    }
    /**control vertical for 4 connect
     * @param user which user 
     * @return //if 4 cell is equal user ,game will finish with return true
     */
    private boolean checkVertical(CellType user) {
        for (int h = 3; h < size; h++) {
            for (int w = 0; w < size; w++) {
                
                if (gameCells[h][w].getType() == user && gameCells[h - 1][w].getType() == user
                        && gameCells[h - 2][w].getType() == user && gameCells[h - 3][w].getType() == user) {
                    return true;
                }
            }
        }
        return false;
    }

    /**control diognal right for 4 connect
     * @param user which user 
     * @return //if 4 cell is equal user ,game will finish with return true
     */
    private boolean checkDiognalRight(CellType user) {
        for (int h = 0; h < size - 3; h++) {
            for (int w = 3; w < size; w++) {
                if (gameCells[h][w].getType() == user && gameCells[h + 1][w - 1].getType() == user
                        && gameCells[h + 2][w - 2].getType() == user && gameCells[h + 3][w - 3].getType() == user) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**control diognal left for 4 connect
     * @param user which user 
     * @return //if 4 cell is equal user ,game will finish with return true
     */
    private boolean checkDiognalLeft(CellType user) {
        for (int h = 0; h < size - 3; h++) {
            for (int w = 0; w < size - 3; w++) {
                if (gameCells[h][w].getType() == user && gameCells[h + 1][w + 1].getType() == user
                        && gameCells[h + 2][w + 2].getType() == user && gameCells[h + 3][w + 3].getType() == user) {
                    return true;
                }
            }
        }
        return false;
    }
    /**control all check function to have winner or not ?
     * @param user which user 
     * @return 1 == User 1 is winner, 2 == User 2 is winner, 3 == It is draw
     * @return 0 == game is not finsihed yet
     */
    private int checkWinner(CellType user) {

        //if There are 4 'X' connect,user2 will be win
        if (user == CellType.USER_1) {
            //control horizonal,vertical and diognal
            if (checkHorizonal(user) || checkVertical(user)
                    || checkDiognalRight(user) || checkDiognalLeft(user)) {
                return 1;
            }
        }

        //if There are 4 'O' connect,user2 will be win
        if (user == CellType.USER_2) {
            if (checkHorizonal(user) || checkVertical(user)
                    || checkDiognalRight(user) || checkDiognalLeft(user)) {
                return 2;
            }
        }

        //if every cell is full,it will be draw
        if (turn == size * size - 1) {
            return 3;
        }

        return 0;

    }

}
