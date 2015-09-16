package s23634.mappe1.com.hangman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by Hans Christian on 16.09.2015.
 */
public class LetterAdapter extends BaseAdapter {
    private String[] letters;
    private LayoutInflater letterInflater;

    public LetterAdapter(Context c) {
        letters = new String[26];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = "" + (char) (i + 'A');
        }
        letterInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return letters.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button letterBtn;
        if (convertView == null) {
            letterBtn = (Button) letterInflater.inflate(R.layout.letter, parent, false);
        } else {
            letterBtn = (Button) convertView;
        }
        letterBtn.setText(letters[position]);
        return letterBtn;
    }

}
