package avd.Common;

import java.util.ArrayList;

/**
 * Created by avdmy on 5/6/2017.
 */
public class GameUtils {
    public static int boardUtility(char[][] board, char player){
        int xWeights = 0, oWeights = 0;
        int[][] positionWeights = {{99, -8, 8, 6, 6, 8, -8, 99}, {-8, -24, -4, -3, -3, -4, -24, -8}, {8, -4, 7, 4, 4, 7, -4, 8},
                {6, -3, 4, 0, 0, 4, -3, 6}, {6, -3, 4, 0, 0, 4, -3, 6}, {8, -4, 7, 4, 4, 7, -4, 8},
                {-8, -24, -4, -3, -3, -4, -24, -8}, {99, -8, 8, 6, 6, 8, -8, 99}};
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 'X'){
                    xWeights += positionWeights[i][j];
                }
                else if(board[i][j] == 'O'){
                    oWeights += positionWeights[i][j];
                }
            }
        }
        return (oWeights - xWeights) * (player == 'X' ? -1 : 1);
    }

    public static char[][] actionResult(char[][] board, int x, int y, char player) {
        char opponent = player == 'X' ? 'O' : 'X';
        int d = 1;
        if (x > 0 && y > 0 && (board[x - 1][y - 1] == opponent)) {
            d = 1;
            while (board[x - d][y - d] == opponent) {
                d++;
                if (x < d || y < d) {
                    d--;
                    break;
                }
                if (board[x - d][y - d] == player) {
                    while (d > 0) {
                        board[x - d][y - d] = player;
                        d--;
                    }
                }
            }
        }
        if (x > 0 && (board[x - 1][y] == opponent)) {
            d = 1;
            while (board[x - d][y] == opponent) {
                d++;
                if (x < d) {
                    d--;
                    break;
                }
                if (board[x - d][y] == player) {
                    while (d > 0) {
                        board[x - d][y] = player;
                        d--;
                    }
                }
            }
        }
        if (x > 0 && y < board[0].length - 1 && (board[x - 1][y + 1] == opponent)) {
            d = 1;
            while (board[x - d][y + d] == opponent) {
                d++;
                if (x < d || y + d >= board[0].length) {
                    d--;
                    break;
                }
                if (board[x - d][y + d] == player) {
                    while (d > 0) {
                        board[x - d][y + d] = player;
                        d--;
                    }
                }
            }
        }
        if (y > 0 && (board[x][y - 1] == opponent)) {
            d = 1;
            while (board[x][y - d] == opponent) {
                d++;
                if (y < d) {
                    d--;
                    break;
                }
                if (board[x][y - d] == player) {
                    while (d > 0) {
                        board[x][y - d] = player;
                        d--;
                    }
                }
            }
        }
        if (y < board[0].length - 1 && (board[x][y + 1] == opponent)) {
            d = 1;
            while (board[x][y + d] == opponent) {
                d++;
                if (y + d >= board[0].length) {
                    d--;
                    break;
                }
                if (board[x][y + d] == player) {
                    while (d > 0) {
                        board[x][y + d] = player;
                        d--;
                    }
                }
            }
        }
        if (x < board[0].length - 1 && (board[x + 1][y] == opponent)) {
            d = 1;
            while (board[x + d][y] == opponent) {
                d++;
                if (x + d >= board[0].length) {
                    d--;
                    break;
                }
                if (board[x + d][y] == player) {
                    while (d > 0) {
                        board[x + d][y] = player;
                        d--;
                    }
                }
            }
        }
        if (x < board[0].length - 1 && y < board[0].length - 1 && (board[x + 1][y + 1] == opponent)) {
            d = 1;
            while (board[x + d][y + d] == opponent) {
                d++;
                if (x + d >= board[0].length || y + d >= board[0].length) {
                    d--;
                    break;
                }
                if (board[x + d][y + d] == player) {
                    while (d > 0) {
                        board[x + d][y + d] = player;
                        d--;
                    }
                }
            }
        }
        return board;
    }

    public static boolean hasBothPlayers(char[][] board){
        boolean hasX = false, hasO = false;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length ; j++){
                if(hasO && hasX){
                    return true;
                }
                if(board[i][j] == 'X') {
                    hasX = true;
                }else if(board[i][j] == 'O'){
                    hasO = true;
                }
            }
        }
        return false;
    }

    public static ArrayList<int[]> generateValidActions(char[][] board, char player){
        char opponent = player=='X'?'O':'X';
        ArrayList<int[]> actions = new ArrayList<int[]>();
        // TODO: 5/6/2017
        return actions;
    }
}
