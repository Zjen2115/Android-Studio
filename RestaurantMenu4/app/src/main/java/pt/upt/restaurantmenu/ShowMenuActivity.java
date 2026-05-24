package pt.upt.restaurantmenu;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowMenuActivity extends AppCompatActivity {

    public static String KEY_DAY = "KEY_DAY";
    public static String KEY_SOUP = "KEY_SOUP";
    public static String KEY_MEAT = "KEY_MEAT";
    public static String KEY_FISH = "KEY_FISH";
    public static String KEY_DESSERT = "KEY_DESSERT";

    TextView textViewDay;
    TextView textViewSoup;
    TextView textViewMeat;
    TextView textViewFish;
    TextView textViewDessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu);

        Intent intent = getIntent();

        int day = intent.getIntExtra(KEY_DAY, 0);
        String soup = intent.getStringExtra(KEY_SOUP);
        String meat = intent.getStringExtra(KEY_MEAT);
        String fish = intent.getStringExtra(KEY_FISH);
        String dessert = intent.getStringExtra(KEY_DESSERT);

        textViewDay = findViewById(R.id.textViewDayDetail);
        textViewSoup = findViewById(R.id.textViewSoupDetail);
        textViewMeat = findViewById(R.id.textViewMeatDetail);
        textViewFish = findViewById(R.id.textViewFishDetail);
        textViewDessert = findViewById(R.id.textViewDessertDetail);

        textViewDay.setText(getDayName(day));
        textViewSoup.setText("Soup: " + soup);
        textViewMeat.setText("Meat dish: " + meat);
        textViewFish.setText("Fish dish: " + fish);
        textViewDessert.setText("Dessert: " + dessert);
    }

    private String getDayName(int day) {
        switch (day) {
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                return "Day " + day;
        }
    }

    public void goBack(View view) {
        finish();
    }
}
