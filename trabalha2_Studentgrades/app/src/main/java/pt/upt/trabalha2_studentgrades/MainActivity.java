package pt.upt.trabalha2_studentgrades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewYears;
    TextView textViewTitleAverage;
    TextView textViewStudentNumber;
    TextView textViewStudentName;

    ArrayList<StudentTest> allTests;      // all grades of all tests
    ArrayList<String> yearTitles;         // "Year 1", "Year 2", ...
    ArrayList<String> yearSubtitles;      // "Average: 14.50", ...
    ArrayList<Integer> yearList;          // 1,2,3...

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTitleAverage = findViewById(R.id.textViewTitleAverage);
        textViewStudentNumber = findViewById(R.id.textViewStudentNumber);
        textViewStudentName = findViewById(R.id.textViewStudentName);
        recyclerViewYears = findViewById(R.id.recyclerViewYears);

        recyclerViewYears.setLayoutManager(new LinearLayoutManager(this));

        allTests = new ArrayList<>();
        yearTitles = new ArrayList<>();
        yearSubtitles = new ArrayList<>();
        yearList = new ArrayList<>();

        createAllTests();     // fill allTests
        loadYearAverages();   // compute average per year and fill RV
    }

    private void createAllTests() {
        allTests.clear();

        // YEAR 1 – Bases de Dados
        allTests.add(new StudentTest(1, "Written test", 14.5));
        allTests.add(new StudentTest(1, "Trabalha 1", 16.0));
        allTests.add(new StudentTest(1, "Trabalha 2", 17.0));

        // YEAR 2 – Laboratório de Tecnologias Web
        allTests.add(new StudentTest(2, "Written test", 18.0));
        allTests.add(new StudentTest(2, "Trabalha 1", 14.0));
        allTests.add(new StudentTest(2, "Trabalha 2", 16.5));

        // YEAR 3 – Laboratory of Data Analysis
        allTests.add(new StudentTest(3, "Written test", 18.0));
        allTests.add(new StudentTest(3, "Trabalha 1", 17.0));
        allTests.add(new StudentTest(3, "Trabalha 2", 16.0));
    }

    private void loadYearAverages() {
        yearTitles.clear();
        yearSubtitles.clear();
        yearList.clear();

        // here we know we have years 1,2,3 only
        for (int year = 1; year <= 3; year++) {
            double sum = 0;
            int count = 0;

            for (StudentTest t : allTests) {
                if (t.getYear() == year) {
                    sum += t.getGrade();
                    count++;
                }
            }

            if (count > 0) {
                double avg = sum / count;
                yearList.add(year);
                yearTitles.add("Year " + year);
                yearSubtitles.add(String.format("Average: %.2f", avg));
            }
        }

        adapter = new MyAdapter(yearTitles, yearSubtitles, position -> {
            int year = yearList.get(position);
            Intent intent = new Intent(MainActivity.this, YearDetailActivity.class);
            intent.putExtra("year", year);
            startActivity(intent);
        });

        recyclerViewYears.setAdapter(adapter);
    }
}
