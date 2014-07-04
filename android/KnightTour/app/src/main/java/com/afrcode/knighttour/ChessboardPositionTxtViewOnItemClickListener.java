package com.afrcode.knighttour;

import android.view.View;

import static com.afrcode.knighttour.KnightTourChessboardAdapter.CHESSBOARD_COLUMNS_X_LINES;

/**
 * Created by alysson on 04/07/2014.
 */
public class ChessboardPositionTxtViewOnItemClickListener implements View.OnClickListener {
    private int position;

    public ChessboardPositionTxtViewOnItemClickListener(int position) {
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        int line = position / CHESSBOARD_COLUMNS_X_LINES;
        int column = position % CHESSBOARD_COLUMNS_X_LINES;
        ChessboardKnightTour.getInstance().walkFrom(line, column, view);
    }

}
