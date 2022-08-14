/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.client;

/**
 *
 * @author Djina
 */
public class Connect4ComputerPlayer {
    
    public static enum gameState {
        INVALID, FULL, DRAW, CONTINUE, XWin, OWin;
    }
    
    public char[][] grid;
    public final int rows = 6;
    public final int cols = 7;
    
    public Connect4ComputerPlayer() {
        grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = ' ';
            }
        }
    }
    
    public gameState updateGrid(int column, char player) {

        boolean winState;
        int col = column;

        // check if the column is within 0-6
        if (col < 0 || col > 6) {
            return gameState.INVALID;
        }

        // check if the column is full
        if (grid[0][col] == 'X' || grid[0][col] == 'O') {
            return gameState.FULL;
        }

        // check if it is a draw
        boolean isDraw = true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == ' ') {
                    isDraw = false;
                }
            }
        }
        if (isDraw == true) {
            return gameState.DRAW;
        }

        // check if anyone wins
        winState = checkWinState(col, player);
        if (winState == true) {
            if (player == 'X') {
                return gameState.XWin;
            } else {
                return gameState.OWin;
            }
        }

        // still playing, no one wins.
        return gameState.CONTINUE;
    }
    
    private boolean checkWinState(int col, char player) {

        // first add the coin into grid.
        int rowCount = rows - 1;
        while (grid[rowCount][col] != ' ') {
            rowCount--;
        }
        grid[rowCount][col] = player;

        /* check grid horizontally. */
        int countX = 0;
        int countO = 0;
        // check every column in each row if there is a win.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'X') {
                    // check if previous coin is the same as this one.
                    if (j == 0) {
                        countX++;
                    } else if (j > 0 && (grid[i][j - 1] != 'X')) {
                        countX = 1;
                        countO = 0;
                    } else if (j > 0 && (grid[i][j - 1] == 'X')) {
                        countX++;
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                }
                // check if previous coin is the same as this one.
                else if (grid[i][j] == 'O') {
                    if (j == 0) {
                        countO++;
                    } else if (j > 0 && (grid[i][j - 1] != 'O')) {
                        countO = 1;
                        countX = 0;
                    } else if (j > 0 && (grid[i][j - 1] == 'O')) {
                        countX++;
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                } else {
                    countX = 0;
                    countO = 0;
                }
                // if 4 same coins appear.
                if (countX == 4 || countO == 4) {
                    System.out.println("Horizonally");
                    return true;
                }
            }
            countX = 0;
            countO = 0;
        }

        /* check grid vertically. */
        // reset countX & countO.
        countX = 0;
        countO = 0;
        // check every row in each column if there is a win.
        for (int i = 0; i < cols; i++) {
            for (int j = rows - 1; j >= 0; j--) {
                if (grid[j][i] == 'X') {
                    // check if previous coin is the same as this one.
                    if (j == rows - 1) {
                        countX++;
                    } else if (j < rows - 1 && (grid[j + 1][i] != 'X')) {
                        countX = 1;
                    } else if ((j < rows - 1) && (grid[j + 1][i] == 'X')) {
                        countX++;
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                }
                // check if previous coin is the same as this one.
                else if (grid[j][i] == 'O') {
                    if (j == rows - 1) {
                        countO++;
                    } else if ((j < rows - 1) && (grid[j + 1][i] != 'O')) {
                        countO = 1;
                    } else if ((j < rows - 1) && (grid[j + 1][i] == 'O')) {
                        countO++;
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                } else {
                    countX = 0;
                    countO = 0;
                }
                // if 4 same coins appear.
                if (countX == 4 || countO == 4) {
                    System.out.println("Vertically");
                    return true;
                }
            }
            countX = 0;
            countO = 0;
        }

        /* check grid diagonally. */
        // check left to right, top to bottom diagonal.
        // reset countX & countO.
        countX = 0;
        countO = 0;
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < cols - 3; j++) {
                for (int k = 0; k < 4; k++) {
                    if (grid[i + k][j + k] == 'X') {
                        // check if the previous is the same as this one.
                        if (k == 0) {
                            countX++;
                        } else if (k > 0 && grid[i + k - 1][j + k - 1] == 'X') {
                            countX++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    }
                    // check if the previous is the same as this one.
                    else if (grid[i + k][j + k] == 'O') {
                        if (k == 0) {
                            countO++;
                        } else if (k > 0 && grid[i + k - 1][j + k - 1] == 'O') {
                            countO++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                    // if 4 same coins appear.
                    if (countX == 4 || countO == 4) {
                        System.out.println("Diagonal, L-R, Top-Bom");
                        return true;
                    }
                }
            }
        }

        // check left to right, bottom to top diagonal.
        // reset countX & countO.
        countX = 0;
        countO = 0;
        for (int i = rows - 1; i > 3; i--) {
            for (int j = 0; j < cols - 3; j++) {
                for (int k = 0; k < 4; k++) {
                    if (grid[i - k][j + k] == 'X') {
                        // check if the previous is the same as this one.
                        if (k == 0) {
                            countX++;
                        } else if (k > 0 && grid[i - k + 1][j + k - 1] == 'X') {
                            countX++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else if (grid[i - k][j + k] == 'O') {
                        // check if the previous is the same as this one.
                        if (k == 0) {
                            countO++;
                        } else if (k > 0 && grid[i - k + 1][j + k - 1] == 'O') {
                            countO++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                    // if 4 same coins appear.
                    if (countX == 4 || countO == 4) {
                        System.out.println("Diagonal, L-R, Bom-Top");
                        return true;
                    }
                }
            }
        }

        // check right to left, top to bottom diagonal.
        // reset countX & countO.
        countX = 0;
        countO = 0;
        for (int i = 0; i < rows - 3; i++) {
            for (int j = cols - 1; j > 3; j--) {
                for (int k = 0; k < 4; k++) {
                    if (grid[i + k][j - k] == 'X') {
                        // check if the previous is the same as this one.
                        if (k == 0) {
                            countX++;
                        } else if (k > 0 && grid[i + k - 1][j - k + 1] == 'X') {
                            countX++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    }
                    // check if the previous is the same as this one.
                    else if (grid[i + k][j - k] == 'O') {
                        if (k == 0) {
                            countO++;
                        } else if (k > 0 && grid[i + k - 1][j - k + 1] == 'O') {
                            countO++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                    // if 4 same coins appear
                    if (countX == 4 || countO == 4) {
                        System.out.println("Diagonal, R-L, Top-Bom");
                        return true;
                    }
                }
            }
        }

        // check right to left, bottom to top diagonal.
        // reset countX & countO.
        countX = 0;
        countO = 0;
        for (int i = rows - 1; i > 3; i--) {
            for (int j = cols - 1; j > 3; j--) {
                for (int k = 0; k < 4; k++) {
                    if (grid[i - k][j - k] == 'X') {
                        // check if the previous is the same as this one.
                        if (k == 0) {
                            countX++;
                        } else if (k > 0 && grid[i - k + 1][j - k + 1] == 'X') {
                            countX++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else if (grid[i - k][j - k] == 'O') {
                        // check if the previous is the same as this one.
                        if (k == 0) {
                            countO++;
                        } else if (k > 0 && grid[i - k + 1][j - k + 1] == 'O') {
                            countO++;
                        } else {
                            countX = 0;
                            countO = 0;
                        }
                    } else {
                        countX = 0;
                        countO = 0;
                    }
                    // if 4 same coins appear
                    if (countX == 4 || countO == 4) {
                        System.out.println("Diagonal, R-L, Bom-Top");
                        return true;
                    }
                }
            }
        }

        // no one wins for now.
        return false;
    }

    /**
     * return rows.
     * 
     * @return rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * return columns.
     * 
     * @return cols
     */
    public int getColumns() {
        return cols;
    }

    public int getFirstEmptyRow(int col) {
        int coln = col;
        for (int i = rows - 1; i >= 0; i--) {
            if (grid[i][coln] == ' ') {
                return i;
            }
        }
        return -1;
    }

    /**
     * Print the board (grid) to the console.
     */
    public void printGrid() {
        for (int i = 0; i < rows; i++) {
            System.out.print('|');
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j]);
                System.out.print('|');
            }
            System.out.println();
        }
    }
    
}
