package s23634.mappe1.com.hangman;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private String[] words;
    private Random random;
    private String current_word;
    private LinearLayout wordLayout;
    private TextView[] charViews;
    private GridView letters;
    private LetterAdapter ltrAdapt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Resources res = getResources();
        words = res.getStringArray(R.array.dictionary);

        random = new Random();
        current_word = "";
        wordLayout = (LinearLayout) findViewById(R.id.word);

        letters = (GridView) findViewById(R.id.letters);
        playgame();
    }

    private void playgame() {
        String newWord = words[random.nextInt(words.length)];
        while (newWord.equals(current_word))
            newWord = words[random.nextInt(words.length)];
        current_word = newWord;

        charViews = new TextView[current_word.length()];

        wordLayout.removeAllViews();
        for (int i = 0; i < current_word.length(); i++) {
            charViews[i] = new TextView(this);
            charViews[i].setText("" + current_word.charAt(i));

            charViews[i].setLayoutParams(new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            charViews[i].setGravity(Gravity.CENTER);
            charViews[i].setTextColor(Color.WHITE);
            charViews[i].setBackgroundResource(R.drawable.letter_bg);

            wordLayout.addView(charViews[i]);

            ltrAdapt = new LetterAdapter(this);
            letters.setAdapter(ltrAdapt);
        }
    }

}
