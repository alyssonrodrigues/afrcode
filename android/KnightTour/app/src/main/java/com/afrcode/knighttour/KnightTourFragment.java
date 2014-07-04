package com.afrcode.knighttour;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by alysson on 04/07/2014.
 */
public class KnightTourFragment extends Fragment {

    public KnightTourFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_knight_tour, container, false);

        GridView chessboardGridView = GridView.class.cast(
                rootView.findViewById(R.id.chessboardGridView));
        chessboardGridView.setAdapter(new KnightTourChessboardAdapter(getActivity()));

        return rootView;
    }
}