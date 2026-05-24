package pt.upt.trabalha1_quizapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Activity3 extends AppCompatActivity {

    RadioGroup radioGroupQ3;

    int scoreSoFar;
    int q1UserIndex;

    // Q3 data (arrays inside Activity)
    String[] questions = {
            "How many languages are spoken in India (according to the Census)?"
    };

    String[][] options = {
            {
                    "A) 22",
                    "B) 121",
                    "C) 1,652"
            }
    };

    int[] correctIndexes = {1}; // B) 121 is correct (index 1)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        radioGroupQ3 = findViewById(R.id.radioGroupQ3);

        Intent intent = getIntent();
        scoreSoFar = intent.getIntExtra("scoreSoFar", 0);
        q1UserIndex = intent.getIntExtra("q1UserIndex", -1);
    }

    private int getQ3SelectedIndex() {
        int checkedId = radioGroupQ3.getCheckedRadioButtonId();

        if (checkedId == R.id.radioQ3A) {
            return 0;
        } else if (checkedId == R.id.radioQ3B) {
            return 1;
        } else if (checkedId == R.id.radioQ3C) {
            return 2;
        } else {
            return -1;
        }
    }

    public void showEvaluationFromType2(View view) {
        int userIndexQ3 = getQ3SelectedIndex();
        int scoreQ3 = 0;

        if (userIndexQ3 == correctIndexes[0]) {
            scoreQ3 = 1;
        }

        int totalScore = scoreSoFar + scoreQ3;

        Intent intent = new Intent(this, Activity4.class);
        intent.putExtra("fromType", 2); // Type 2 → Q3
        intent.putExtra("totalScore", totalScore);
        intent.putExtra("q1UserIndex", q1UserIndex);
        intent.putExtra("q3UserIndex", userIndexQ3);
        startActivity(intent);
    }
}
