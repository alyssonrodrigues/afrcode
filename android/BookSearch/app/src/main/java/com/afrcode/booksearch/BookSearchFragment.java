package com.afrcode.booksearch;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by alysson on 05/07/2014.
 */
public class BookSearchFragment extends Fragment {
    private static final String QUERY_URL = "http://openlibrary.org/search.json?q=";

    public BookSearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_search, container, false);

        ListView bookItemsView = ListView.class.cast(rootView.findViewById(
                R.id.bookItemsView));
        final BookItemJSONAdapter bookItemsAdapter = new BookItemJSONAdapter(getActivity(),
                inflater);
        bookItemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject jsonObject = JSONObject.class.cast(bookItemsAdapter.getItem(position));
                String coverID = jsonObject.optString("cover_i","");
                Intent bookDetailIntent = new Intent(getActivity(), BookDetailActivity.class);
                bookDetailIntent.putExtra("coverID", coverID);
                startActivity(bookDetailIntent);
            }
        });
        bookItemsView.setAdapter(bookItemsAdapter);

        final EditText searchInput = EditText.class.cast(rootView.findViewById(
                R.id.bookSearchInput));
        Button searchCommandButton = Button.class.cast(rootView.findViewById(
                R.id.bookSearchCommand));
        searchCommandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookToSearch = searchInput.getText().toString();
                queryBooks(bookToSearch, bookItemsAdapter);
            }
        });

        return rootView;
    }

    private void queryBooks(String searchString, final BookItemJSONAdapter bookItemsAdapter) {
        String urlString = "";
        try {
            urlString = URLEncoder.encode(searchString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(getTag(), e.getMessage(), e);
            Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        getActivity().setProgressBarIndeterminateVisibility(true);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(QUERY_URL + urlString, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                getActivity().setProgressBarIndeterminateVisibility(false);
                Log.d(getTag(), response.toString());
                bookItemsAdapter.updateData(response.optJSONArray("docs"));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                getActivity().setProgressBarIndeterminateVisibility(false);
                Log.d(getTag(), throwable.getMessage(), throwable);
            }
        });
    }

}
