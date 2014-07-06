package com.afrcode.booksearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by alysson on 05/07/2014.
 */
public class BookItemJSONAdapter extends BaseAdapter {
    public static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/";

    private Context mContext;
    private LayoutInflater mInflater;
    private JSONArray mJsonArray;

    public BookItemJSONAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        mJsonArray = new JSONArray();
    }

    @Override
    public int getCount() {
        return mJsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        return mJsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_book_item, null);
            holder = new ViewHolder();
            holder.thumbnailImageView = ImageView.class.cast(
                    convertView.findViewById(R.id.img_thumbnail));
            holder.titleTextView = TextView.class.cast(convertView.findViewById(R.id.text_title));
            holder.authorTextView = TextView.class.cast(convertView.findViewById(R.id.text_author));
            convertView.setTag(holder);
        } else {
            holder = ViewHolder.class.cast(convertView.getTag());
        }
        JSONObject jsonObject = JSONObject.class.cast(getItem(position));
        setCoverImg(holder, jsonObject);
        setAuthorTitle(holder, jsonObject);
        return convertView;
    }

    private void setAuthorTitle(ViewHolder holder, JSONObject jsonObject) {
        String bookTitle = "";
        String authorName = "";
        if (jsonObject.has("title")) {
            bookTitle = jsonObject.optString("title");
        }
        if (jsonObject.has("author_name")) {
            authorName = jsonObject.optJSONArray("author_name").optString(0);
        }
        holder.titleTextView.setText(bookTitle);
        holder.authorTextView.setText(authorName);
    }

    private void setCoverImg(ViewHolder holder, JSONObject jsonObject) {
        if (jsonObject.has("cover_i")) {
            String imageID = jsonObject.optString("cover_i");
            String imageURL = IMAGE_URL_BASE + imageID + "-S.jpg";
            Picasso.with(mContext).load(imageURL).placeholder(R.drawable.ic_books).into(
                    holder.thumbnailImageView);
        } else {
            holder.thumbnailImageView.setImageResource(R.drawable.ic_books);
        }
    }

    public void updateData(JSONArray jsonArray) {
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public ImageView thumbnailImageView;
        public TextView titleTextView;
        public TextView authorTextView;
    }
}
