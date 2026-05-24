package pt.upt.trabalha1_quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroupQ1;

    // Q1 data (arrays inside Activity)
    String[] questions = {
            "Which of the following best describes ethical behaviour in the workplace?"
    };

    String[][] options = {
            {
                    "A) Following rules only when convenient",
                    "B) Acting with honesty and integrity",
                    "C) Prioritising personal gain"
            }
    };

    int[] correctIndexes = {1}; // B) is correct (index 1)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupQ1 = findViewById(R.id.radioGroupQ1);
        // Text for question + options comes from XML text, so no need to set here
    }

    private int getQ1SelectedIndex() {
        int checkedId = radioGroupQ1.getCheckedRadioButtonId();

        if (checkedId == R.id.radioQ1A) {
            return 0;
        } else if (checkedId == R.id.radioQ1B) {
            return 1;
        } else if (checkedId == R.id.radioQ1C) {
            return 2;
        } else {
            return -1; // no answer selected
        }
    }

    public void exploreType1(View view) {
        int userIndexQ1 = getQ1SelectedIndex();
        int scoreSoFar = 0;

        if (userIndexQ1 == correctIndexes[0]) {
            scoreSoFar = 1;
        }

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("scoreSoFar", scoreSoFar);
        intent.putExtra("q1UserIndex", userIndexQ1);
        startActivity(intent);
    }

    public void exploreType2(View view) {
        int userIndexQ1 = getQ1SelectedIndex();
        int scoreSoFar = 0;

        if (userIndexQ1 == correctIndexes[0]) {
            scoreSoFar = 1;
        }

        Intent intent = new Intent(this, Activity3.class);
        intent.putExtra("scoreSoFar", scoreSoFar);
        intent.putExtra("q1UserIndex", userIndexQ1);
        startActivity(intent);
    }
}
