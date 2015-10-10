package software.ryancook.engine;

import software.ryancook.Board;
import java.util.*;

public class PositionTable
{
    private Map<Integer, PositionResult> table;

    public PositionTable()
    {
        table = new HashMap<>();
    }

    public void put(Board board, int score, int evaluationDepth)
    {
        PositionResult result = new PositionResult(board.getPly(), score, evaluationDepth);
        table.put(board.hashCode(), result);
    }

    public int size()
    {
        return table.size();
    }

    public boolean hasPosition(Board board)
    {
        return table.containsKey(board.hashCode());
    }

    public int getScore(Board board)
    {
        return table.get(board.hashCode()).getScore();
    }

    public int getEvaluationDepth(Board board)
    {
        return table.get(board.hashCode()).getEvaluationDepth();
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
