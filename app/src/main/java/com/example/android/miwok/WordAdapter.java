package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
            super(context, 0, words);
            mColorResourceId = colorResourceId;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

            Word currentWord = getItem(position);

            // Find the TextView in the list_item.xml layout with the ID version_name
            TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
            // Get the version name from the current AndroidFlavor object and
            // set this text on the name TextView
            miwokTextView.setText(currentWord.getMiwokTranslation());

            // Find the TextView in the list_item.xml layout with the ID version_number
            TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
            // Get the version number from the current AndroidFlavor object and
            // set this text on the number TextView
            defaultTextView.setText(currentWord.getDefaultTranslation());

            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

            if(currentWord.hasImage()) {
                imageView.setImageResource(currentWord.getImageResourceId());
                imageView.setVisibility(View.VISIBLE);
            }
            else{
                imageView.setVisibility(View.GONE);
            }

            View textContainer = listItemView.findViewById(R.id.text_container);
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            textContainer.setBackgroundColor(color);

            // Return the whole list item layout (containing 2 TextViews and an ImageView)
            // so that it can be shown in the ListView
            return listItemView;
    }
}
