package avd.ai.bot;

import java.util.List;

/**
 * Created by avdmy on 5/6/2017.
 */
public class AIPlayer {
    private final char player;
    private int[] nextMove;

    public AIPlayer(char player) {
        this.player = player;
    }

    public int[] getNextMove(){
        return nextMove;
    }

    private int alphaBetaSearch(){
        return 0;
    }

    private int maxValue(){
        return 0;
    }

    private int minValue(){
        return 0;
    }

}
