package s23634.mappe1.com.hangman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
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

    private ImageView[] bodyParts;
    private int numParts = 6;
    private int currPart;
    private int numChars;
    private int numCorr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Resources res = getResources();
        words = res.getStringArray(R.array.dictionary);

        random = new Random();
        current_word = "";
        wordLayout = (LinearLayout) findViewById(R.id.word);

        letters = (GridView) findViewById(R.id.letters);

        bodyParts = new ImageView[numParts];
        bodyParts[0] = (ImageView) findViewById(R.id.head);
        bodyParts[1] = (ImageView) findViewById(R.id.body);
        bodyParts[2] = (ImageView) findViewById(R.id.left_arm);
        bodyParts[3] = (ImageView) findViewById(R.id.right_arm);
        bodyParts[4] = (ImageView) findViewById(R.id.left_leg);
        bodyParts[5] = (ImageView) findViewById(R.id.right_leg);

        playgame();
    }

    public void letterPressed(View view) {
        String ltr = ((TextView) view).getText().toString();
        char letterChar = ltr.charAt(0);
        boolean correct = false;

        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.letter_down);
        for (int i = 0; i < current_word.length(); i++) {
            if (current_word.charAt(i) == letterChar) {
                correct = true;
                numCorr++;
                charViews[i].setTextColor(Color.BLACK);
            }
        }

        if (correct) {
            if (numCorr == numChars) {
                disableBtns();

                AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
                winBuild.setTitle("Way to go!!");
                winBuild.setMessage("You got  " + current_word);
                winBuild.setPositiveButton("One more time", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameActivity.this.playgame();
                    }
                });

                winBuild.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameActivity.this.finish();
                    }
                });

                winBuild.show();
            }
        } else if (currPart < numParts) {
            bodyParts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        } else {
            disableBtns();
            AlertDialog.Builder loseBuild = new AlertDialog.Builder(this);
            loseBuild.setTitle("Noope...");
            loseBuild.setMessage("That`s not right!!\nCorrect answer was " + current_word);
            loseBuild.setPositiveButton("One more time", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    GameActivity.this.playgame();
                }
            });

            loseBuild.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    GameActivity.this.finish();
                }
            });

            loseBuild.show();
        }
    }

    public void disableBtns() {
        int numLetters = letters.getChildCount();
        for (int i = 0; i < numLetters; i++) {
            letters.getChildAt(i).setEnabled(false);
        }
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

            LetterAdapter ltrAdapt = new LetterAdapter(this);
            letters.setAdapter(ltrAdapt);

            currPart = 0;
            numChars = current_word.length();
            numCorr = 0;

            for (int j = 0; j < numParts; j++) {
                bodyParts[j].setVisibility(View.INVISIBLE);
            }
        }
    }
}
