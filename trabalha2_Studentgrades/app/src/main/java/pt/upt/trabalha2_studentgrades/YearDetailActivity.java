package pt.upt.trabalha2_studentgrades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class YearDetailActivity extends AppCompatActivity {

    TextView textViewYearTitle;
    TextView textViewSubject;
    RecyclerView recyclerViewTests;
    Button buttonBack;

    ArrayList<String> testTitles;
    ArrayList<String> testSubtitles;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_detail);

        textViewYearTitle = findViewById(R.id.textViewYearTitle);
        textViewSubject = findViewById(R.id.textViewSubject);
        recyclerViewTests = findViewById(R.id.recyclerViewTests);
        buttonBack = findViewById(R.id.buttonBack);

        recyclerViewTests.setLayoutManager(new LinearLayoutManager(this));

        int year = getIntent().getIntExtra("year", 1);

        textViewYearTitle.setText("Year " + year + " - Tests");
        textViewSubject.setText(getSubjectName(year));

        testTitles = new ArrayList<>();
        testSubtitles = new ArrayList<>();

        createTestsForYear(year);

        adapter = new MyAdapter(testTitles, testSubtitles, null);
        recyclerViewTests.setAdapter(adapter);

        buttonBack.setOnClickListener(v -> finish());
    }

    private String getSubjectName(int year) {
        if (year == 1) {
            return "Bases de Dados";
        } else if (year == 2) {
            return "Laboratório de Tecnologias Web";
        } else {
            return "Laboratory of Data Analysis";
        }
    }

    private void createTestsForYear(int year) {
        testTitles.clear();
        testSubtitles.clear();

        if (year == 1) {
            testTitles.add("Written test");
            testSubtitles.add(String.format("Grade: %.2f", 14.5));

            testTitles.add("Trabalha 1");
            testSubtitles.add(String.format("Grade: %.2f", 16.0));

            testTitles.add("Trabalha 2");
            testSubtitles.add(String.format("Grade: %.2f", 17.0));

        } else if (year == 2) {
            testTitles.add("Written test");
            testSubtitles.add(String.format("Grade: %.2f", 18.0));

            testTitles.add("Trabalha 1");
            testSubtitles.add(String.format("Grade: %.2f", 14.0));

            testTitles.add("Trabalha 2");
            testSubtitles.add(String.format("Grade: %.2f", 16.0));

        } else {
            testTitles.add("Written test");
            testSubtitles.add(String.format("Grade: %.2f", 18.0));

            testTitles.add("Trabalha 1");
            testSubtitles.add(String.format("Grade: %.2f", 17.0));

            testTitles.add("Trabalha 2");
            testSubtitles.add(String.format("Grade: %.2f", 16.0));
        }
    }
}
