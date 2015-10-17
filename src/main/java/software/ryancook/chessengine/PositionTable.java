package software.ryancook.chessengine;

import software.ryancook.chessengine.game.*;
import java.util.*;

public class PositionTable
{
    private Map<Integer, PositionResult> table;

    public PositionTable()
    {
        table = new HashMap<>();
    }

    public void put(ChessGameState gameState, int score, int evaluationDepth)
    {
        PositionResult result = new PositionResult(gameState.getPly(), score, evaluationDepth);
        table.put(gameState.hashCode(), result);
    }

    public int size()
    {
        return table.size();
    }

    public boolean hasPosition(ChessGameState gameState)
    {
        return table.containsKey(gameState.hashCode());
    }

    public int getScore(ChessGameState gameState)
    {
        return table.get(gameState.hashCode()).getScore();
    }

    public int getEvaluationDepth(ChessGameState gameState)
    {
        return table.get(gameState.hashCode()).getEvaluationDepth();
    }

    private class PositionResult
    {
        int ply;
        int score;
        int evaluationDepth;

        PositionResult(int ply, int score, int evaluationDepth)
        {
            this.ply = ply;
            this.score = score;
            this.evaluationDepth = evaluationDepth;
        }

        int getPly()
        {
            return ply;
        }

        int getScore()
        {
            return score;
        }

        int getEvaluationDepth()
        {
            return evaluationDepth;
        }
    }
}
