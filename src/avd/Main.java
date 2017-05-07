package avd;

import avd.Common.GameUtils;
import avd.ai.bot.AIPlayer;

public class Main {

    public static void main(String[] args) {
        char[][] board= {{'*','*','*','*','*','*','*','*'},
                {'*','*','*','*','*','*','*','*'},
                {'*','*','*','*','*','*','*','*'},
                {'*','*','*','X','O','*','*','*'},
                {'*','*','*','O','X','*','*','*'},
                {'*','*','*','*','*','*','*','*'},
                {'*','*','*','*','*','*','*','*'},
                {'*','*','*','*','*','*','*','*'}};
        AIPlayer ai = new AIPlayer('X');
        int[] action = ai.getNextMove(board);
        if(action != null) {
            char[][] nb = GameUtils.actionResult(board, action[0], action[1], 'X');
            printBoard(nb);
        }
    }

    private static void printBoard(char[][] board){
        for(int i = 0; i< board.length;i++){
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
