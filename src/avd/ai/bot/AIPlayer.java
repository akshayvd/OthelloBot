package avd.ai.bot;

import avd.Common.GameUtils;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;

/**
 * Created by avdmy on 5/6/2017.
 */
public class AIPlayer {
    private final char player;
    private int[] nextMove;
    private int cutoffDepth = 5;

    public AIPlayer(char player) {
        this.player = player;
    }

    public int[] getNextMove(char[][] board){
        int v = maxValue(board, player, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        return nextMove;
    }

    private int maxValue(char[][] board, char player, int level, int alpha, int beta, int passDepth){
        char opponent = player == 'X'? 'O' : 'X';
        ArrayList<int[]> possibleActions = GameUtils.generateValidActions(board, player);
        if(level == cutoffDepth || passDepth == 2){
            return GameUtils.boardUtility(board, player);
        }
        int v = Integer.MIN_VALUE;
        if(possibleActions.size() == 0){
            v = Math.max(v, minValue(board, opponent, level + 1, alpha, beta, passDepth + 1));
            if(v >= beta){
                return v;
            }
            alpha = Math.max(alpha, v);
        }
        for(int[] action : possibleActions){
            char[][] newBoard = GameUtils.actionResult(board, action[0], action[1], player);
            v =  Math.max(v, minValue(newBoard, opponent, level + 1, alpha, beta, 0));
            if(v >= beta){
                break;
            }
            if(alpha < v){
                alpha = v;
                if (level == 0){
                    this.nextMove = action;
                }
            }
        }
        return v;
    }

    private int minValue(char[][] board, char player, int level, int alpha, int beta, int passDepth){
        char opponent = player == 'X'? 'O' : 'X';
        ArrayList<int[]> possibleActions = GameUtils.generateValidActions(board, player);
        if(level == cutoffDepth || passDepth == 2){
            return GameUtils.boardUtility(board, player);
        }
        int v = Integer.MIN_VALUE;
        if(possibleActions.size() == 0){
            v = Math.max(v, maxValue(board, opponent, level + 1, alpha, beta, passDepth + 1));
            if(v <= alpha){
                return v;
            }
            beta = Math.min(beta, v);
        }
        for(int[] action : possibleActions){
            char[][] newBoard = GameUtils.actionResult(board, action[0], action[1], player);
            v =  Math.max(v, minValue(newBoard, opponent, level + 1, alpha, beta, 0));
            if(v <= alpha){
                break;
            }
            beta = Math.min(beta, v);
        }
        return v;
    }

}
