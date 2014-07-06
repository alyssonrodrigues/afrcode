package com.afrcode.booksearch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static com.afrcode.booksearch.BookItemJSONAdapter.IMAGE_URL_BASE;

/**
 * Created by alysson on 05/07/2014.
 */
public class BookDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imageView = ImageView.class.cast(findViewById(R.id.img_cover));
        String coverID = getIntent().getExtras().getString("coverID");
        if (coverID.length() > 0) {
            String mImageURL = IMAGE_URL_BASE + coverID + "-L.jpg";
             Picasso.with(this).load(mImageURL).placeholder(R.drawable.img_books_loading).into(
                    imageView);
        }
    }
}