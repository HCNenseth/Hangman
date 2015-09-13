package s23634.mappe1.com.hangman;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private String[] words;
    private Random random;
    private String current_word;
    private LinearLayout wordLayout;
    private TextView[] charViews;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Resources res = getResources();
        words = res.getStringArray(R.array.dictionary);

        random = new Random();
        current_word = "";
        wordLayout = (LinearLayout) findViewById(R.id.word);
    }
}
