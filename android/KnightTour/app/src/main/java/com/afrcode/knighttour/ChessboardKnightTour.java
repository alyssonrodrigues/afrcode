package com.afrcode.knighttour;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.afrcode.knighttour.KnightTourChessboardAdapter.CHESSBOARD_COLUMNS_X_LINES;

/**
 * Created by alysson on 04/07/2014.
 */
public class ChessboardKnightTour {
    private static final ChessboardKnightTour instance = new ChessboardKnightTour();
    private int[][] table;

    private ChessboardKnightTour() {
        // Singleton
    }

    public static ChessboardKnightTour getInstance() {
        return instance;
    }

    private void start() {
        table = new int[CHESSBOARD_COLUMNS_X_LINES][CHESSBOARD_COLUMNS_X_LINES];
    }

    public int walkFrom(int line, int column, View knightViewPos) {
        start();
        int steps = 0;
        View currentKnightViewPos = knightViewPos;
        while (table[line][column] != 1) {
            steps++;
            table[line][column] = 1;
            replaceKnight(currentKnightViewPos, steps);

            // Warnsdorff's algorithm
            int minNumMovesFrom = Integer.MAX_VALUE;
            int[] nextMove = {line, column};
            for (int[] move : getPossibleMovesFrom(line, column)) {
                int numMovesFromMove =
                        getPossibleMovesFrom(move[0], move[1]).size();
                if (numMovesFromMove < minNumMovesFrom) {
                    minNumMovesFrom = numMovesFromMove;
                    nextMove = move;
                }
            }
            line = nextMove[0];
            column = nextMove[1];
            GridView currentKnightViewPosParent = GridView.class.cast(
                    currentKnightViewPos.getParent());
            currentKnightViewPos = currentKnightViewPosParent.getChildAt(
                    getPositionIndex(line, column));
        }
        Toast.makeText(currentKnightViewPos.getContext(), R.string.done_msg, Toast.LENGTH_SHORT)
                .show();
        return steps;
    }

    private void replaceKnight(View currentKnightViewPos, int currentStep) {
        TextView chessboardPosTxtView = TextView.class.cast(currentKnightViewPos);
        chessboardPosTxtView.setText(String.valueOf(currentStep));
        chessboardPosTxtView.invalidate();
    }

    private int getPositionIndex(int line, int column) {
        int positionIndex;
        if (line == 0) {
            positionIndex = column;
        } else {
            positionIndex = line * CHESSBOARD_COLUMNS_X_LINES + column;
        }
        return positionIndex;
    }

    private List<int[]> getPossibleMovesFrom(int line, int column) {
        List<int[]> positions = new ArrayList<int[]>();
        if (line + 2 <= CHESSBOARD_COLUMNS_X_LINES - 1) {
            if (column + 1 <= CHESSBOARD_COLUMNS_X_LINES - 1 &&
                    table[line + 2][column + 1] == 0) {
                positions.add(new int[]{line + 2, column + 1});
            }
            if (column - 1 >= 0 &&
                    table[line + 2][column - 1] == 0) {
                positions.add(new int[]{line + 2, column - 1});
            }
        }
        if (line + 1 <= CHESSBOARD_COLUMNS_X_LINES - 1) {
            if (column + 2 <= CHESSBOARD_COLUMNS_X_LINES - 1 &&
                    table[line + 1][column + 2] == 0) {
                positions.add(new int[]{line + 1, column + 2});
            }
            if (column - 2 >= 0 &&
                    table[line + 1][column - 2] == 0) {
                positions.add(new int[]{line + 1, column - 2});
            }
        }
        if (line - 2 >= 0) {
            if (column + 1 <= CHESSBOARD_COLUMNS_X_LINES - 1 &&
                    table[line - 2][column + 1] == 0) {
                positions.add(new int[]{line - 2, column + 1});
            }
            if (column - 1 >= 0 &&
                    table[line - 2][column - 1] == 0) {
                positions.add(new int[]{line - 2, column - 1});
            }
        }
        if (line - 1 >= 0) {
            if (column + 2 <= CHESSBOARD_COLUMNS_X_LINES - 1 &&
                    table[line - 1][column + 2] == 0) {
                positions.add(new int[]{line - 1, column + 2});
            }
            if (column - 2 >= 0 &&
                    table[line - 1][column - 2] == 0) {
                positions.add(new int[]{line - 1, column - 2});
            }
        }
        return positions;
    }

}
