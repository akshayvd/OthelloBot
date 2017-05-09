package avd.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        char[][] newBoard = new char[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            System.arraycopy(board[i], 0, newBoard[i], 0, board[0].length);
        }
        newBoard[x][y] = player;
        char opponent = player == 'X' ? 'O' : 'X';
        int d = 1;
        for(int k : new int[] {-1,0,1}){
            for(int l : new int[] {-1, 0, 1}){
                if( k == 0 && l == 0) continue;
                if(x+k > 0 && y+l > 0 && x+k < newBoard.length-1 && y+l < newBoard[x].length-1 && newBoard[x + k][y + l] == opponent){
                    d = 1;
                    while(newBoard[x + (d*k)][y + (d*l)] == opponent){
                        d++;
                        if(x+(d*k) < 0 || y+(d*l) < 0 || x+(d*k) >= newBoard[x].length || y+(d*l) >= newBoard[x].length){
                            d--;
                            break;
                        }
                    }
                    if (newBoard[x + (d*k)][y + (d*l)] == player){
                        while (d > 0) {
                            newBoard[x + (d*k)][y + (d*l)] = player;
                            d--;
                        }
                    }
                }
            }
        }
        return newBoard;
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
        ArrayList<String> actions = new ArrayList<String>();
        ArrayList<int[]> validActions = new ArrayList<int[]>();
        String action = "";
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == opponent){
                    for(int k : new int[] {-1,0,1}){
                        for(int l : new int[] {-1, 0, 1}){
                            if(k == 0 && l == 0) continue;
                            if(i+k >= 0 && j+l >= 0 && i+k < board.length - 1 && j+l < board[i].length - 1 && board[i+k][j+l] == '*'){
                                action = (i + k) + "," + (j+l);
                                if(!actions.contains(action)){
                                    actions.add(action);
                                }
                            }
                        }
                    }
                }
            }
        }
        for(String item : actions){
            int x, y;
            x = Integer.parseInt(item.split(",")[0]);
            y = Integer.parseInt(item.split(",")[1]);
            int d = 1;
            boolean stopExploring = false;
            for(int k : new int[] {-1,0,1}){
                for(int l : new int[] {-1, 0, 1}){
                    if( k == 0 && l == 0) continue;
                    if(x+k > 0 && y+l > 0 && x+k < board.length-1 && y+l < board[x].length-1 && board[x + k][y + l] == opponent){
                        d = 1;
                        while(board[x + (d*k)][y + (d*l)] == opponent){
                            d++;
                            if(x+(d*k) < 0 || y+(d*l) < 0 || x+(d*k) >= board[x].length || y+(d*l) >= board[x].length){
                                d--;
                                break;
                            }
                        }
                        if (board[x + (d*k)][y + (d*l)] == player){
                            validActions.add(new int[] {x, y});
                            stopExploring = true;
                            break;
                        }
                    }
                }
                if (stopExploring) break;
            }
        }
        Collections.sort(validActions, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if(a[0] - b[0]==0)
                {
                    return a[1]-b[1];
                }
                else
                    return a[0]-b[0];
            }
        });
        return validActions;
    }
}
