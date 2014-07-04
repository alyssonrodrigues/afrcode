package com.afrcode.knighttour;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alysson on 04/07/2014.
 */
public class KnightTourChessboardAdapter extends BaseAdapter {
    public static final int CHESSBOARD_POSITIONS = 64;
    public static final int CHESSBOARD_COLUMNS_X_LINES = 8;
    private Context context;

    public KnightTourChessboardAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return CHESSBOARD_POSITIONS;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView chessboardPositionTxtView = null;
        if (convertView == null) {
            ImageView chessboardPositionImgView = new ImageView(context);
            chessboardPositionImgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            chessboardPositionImgView.setPadding(0, 0, 0, 0);
            int imgId = getImgId(position);
            chessboardPositionImgView.setImageResource(imgId);

            chessboardPositionTxtView = new TextView(context);
            chessboardPositionTxtView.setGravity(Gravity.CENTER);
            chessboardPositionTxtView.setTextColor(Color.BLUE);
            chessboardPositionTxtView.setBackground(chessboardPositionImgView.getDrawable());
            chessboardPositionTxtView.setOnClickListener(
                    new ChessboardPositionTxtViewOnItemClickListener(position));
        } else {
            chessboardPositionTxtView = TextView.class.cast(convertView);
        }
        chessboardPositionTxtView.setText("");
        return chessboardPositionTxtView;
    }

    private int getImgId(int position) {
        int line = position / CHESSBOARD_COLUMNS_X_LINES;
        int imgId;
        if (line % 2 == 0) {
            // even line
            if (position % 2 == 0) {
                // even pos
                imgId = R.drawable.white_pos;
            } else {
                // odd pos
                imgId = R.drawable.black_pos;
            }
        } else {
            // odd line
            if (position % 2 == 0) {
                // even pos
                imgId = R.drawable.black_pos;
            } else {
                // odd pos
                imgId = R.drawable.white_pos;
            }
        }
        return imgId;
    }

    public void restart() {
        notifyDataSetChanged();
    }
}

