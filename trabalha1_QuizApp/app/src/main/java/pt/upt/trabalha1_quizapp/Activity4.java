package pt.upt.trabalha1_quizapp;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity4 extends AppCompatActivity {

    TextView textViewResult;

    // Q1 data
    String q1Text = "Which of the following best describes ethical behaviour in the workplace?";
    String[] q1Options = {
            "A) Following rules only when convenient",
            "B) Acting with honesty and integrity",
            "C) Prioritising personal gain"
    };
    int q1CorrectIndex = 1; // B

    // Q2 data
    String q2Text = "A train is moving at constant speed and you jump inside the train. What happens to your motion?";
    String[] q2Options = {
            "A) You move together with the train",
            "B) You fall backwards",
            "C) You stay completely still in space"
    };
    int q2CorrectIndex = 0; // A

    // Q3 data
    String q3Text = "How many languages are spoken in India (according to the Census)?";
    String[] q3Options = {
            "A) 22",
            "B) 121",
            "C) 1,652"
    };
    int q3CorrectIndex = 1; // B

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        textViewResult = findViewById(R.id.textViewResult);

        int fromType = getIntent().getIntExtra("fromType", 1);
        int totalScore = getIntent().getIntExtra("totalScore", 0);

        int q1UserIndex = getIntent().getIntExtra("q1UserIndex", -1);
        int q2UserIndex = getIntent().getIntExtra("q2UserIndex", -1);
        int q3UserIndex = getIntent().getIntExtra("q3UserIndex", -1);

        StringBuilder sb = new StringBuilder();

        sb.append("Score Breakdown:\n\n");

        // Q1
        sb.append("Q1. ").append(q1Text).append("\n");
        sb.append("User answered: ")
                .append(formatUserAnswer(q1Options, q1UserIndex))
                .append("\n");
        sb.append("Correct answer: ✔ ")
                .append(q1Options[q1CorrectIndex])
                .append("\n\n");

        if (fromType == 1) {
            // Q2
            sb.append("Q2. ").append(q2Text).append("\n");
            sb.append("User answered: ")
                    .append(formatUserAnswer(q2Options, q2UserIndex))
                    .append("\n");
            sb.append("Correct answer: ✔ ")
                    .append(q2Options[q2CorrectIndex])
                    .append("\n\n");
        } else {
            // Q3
            sb.append("Q3. ").append(q3Text).append("\n");
            sb.append("User answered: ")
                    .append(formatUserAnswer(q3Options, q3UserIndex))
                    .append("\n");
            sb.append("Correct answer: ✔ ")
                    .append(q3Options[q3CorrectIndex])
                    .append("\n\n");
        }

        sb.append("The final score is: ").append(totalScore);

        textViewResult.setText(sb.toString());
    }

    private String formatUserAnswer(String[] options, int index) {
        if (index < 0 || index >= options.length) {
            return "No answer";
        }
        return options[index];
    }
}
