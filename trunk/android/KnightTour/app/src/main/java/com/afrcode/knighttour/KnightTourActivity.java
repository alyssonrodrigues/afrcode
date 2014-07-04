package com.afrcode.knighttour;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

public class KnightTourActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knight_tour);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new KnightTourFragment())
                    .commit();
        }
        // greetings msg
        Toast.makeText(this, R.string.greetings_msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.knight_tour, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_restart) {
            return restart();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean restart() {
        GridView gridView = GridView.class.cast(findViewById(R.id.chessboardGridView));
        KnightTourChessboardAdapter adapter = KnightTourChessboardAdapter.class.cast(
                gridView.getAdapter());
        adapter.restart();
        return true;
    }
}
