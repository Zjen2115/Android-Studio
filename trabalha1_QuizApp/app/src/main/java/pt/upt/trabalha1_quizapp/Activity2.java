package pt.upt.trabalha1_quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    RadioGroup radioGroupQ2;

    int scoreSoFar;
    int q1UserIndex;

    // Q2 data (arrays inside Activity)
    String[] questions = {
            "A train is moving at constant speed and you jump inside the train. What happens to your motion?"
    };

    String[][] options = {
            {
                    "A) You move together with the train",
                    "B) You fall backwards",
                    "C) You stay completely still in space"
            }
    };

    int[] correctIndexes = {0}; // A) is correct (index 0)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        radioGroupQ2 = findViewById(R.id.radioGroupQ2);

        Intent intent = getIntent();
        scoreSoFar = intent.getIntExtra("scoreSoFar", 0);
        q1UserIndex = intent.getIntExtra("q1UserIndex", -1);
    }

    private int getQ2SelectedIndex() {
        int checkedId = radioGroupQ2.getCheckedRadioButtonId();

        if (checkedId == R.id.radioQ2A) {
            return 0;
        } else if (checkedId == R.id.radioQ2B) {
            return 1;
        } else if (checkedId == R.id.radioQ2C) {
            return 2;
        } else {
            return -1;
        }
    }

    public void showEvaluationFromType1(View view) {
        int userIndexQ2 = getQ2SelectedIndex();
        int scoreQ2 = 0;

        if (userIndexQ2 == correctIndexes[0]) {
            scoreQ2 = 1;
        }

        int totalScore = scoreSoFar + scoreQ2;

        Intent intent = new Intent(this, Activity4.class);
        intent.putExtra("fromType", 1); // Type 1 → Q2
        intent.putExtra("totalScore", totalScore);
        intent.putExtra("q1UserIndex", q1UserIndex);
        intent.putExtra("q2UserIndex", userIndexQ2);
        startActivity(intent);
    }
}
