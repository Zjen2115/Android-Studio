package pt.upt.restaurantmenu;



import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String MENU_URL = "https://hostingalunos.upt.pt/dam/ementa.csv";

    ExecutorService executorService;
    Handler mainThreadHandler;

    ArrayList<MenuDay> fullMenu = new ArrayList<>();
    ArrayList<MenuDay> otherDaysMenu = new ArrayList<>();

    RecyclerView recyclerView;
    MenuAdapter adapter;

    TextView textViewTodayDay;
    TextView textViewTodaySoup;
    TextView textViewTodayMeat;
    TextView textViewTodayFish;
    TextView textViewTodayDessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executorService = Executors.newSingleThreadExecutor();
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

        textViewTodayDay = findViewById(R.id.textViewTodayDay);
        textViewTodaySoup = findViewById(R.id.textViewTodaySoup);
        textViewTodayMeat = findViewById(R.id.textViewTodayMeat);
        textViewTodayFish = findViewById(R.id.textViewTodayFish);
        textViewTodayDessert = findViewById(R.id.textViewTodayDessert);

        recyclerView = findViewById(R.id.recyclerViewOtherDays);
        adapter = new MenuAdapter(otherDaysMenu);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new LoadMenuTask(executorService, mainThreadHandler, this, MENU_URL);
    }

    public void updateMenu(ArrayList<MenuDay> list) {
        this.fullMenu = list;

        int todayCsvDay = getCsvDayOfWeek();
        MenuDay todayMenu = null;

        for (MenuDay m : fullMenu) {
            if (m.getDayOfWeek() == todayCsvDay) {
                todayMenu = m;
                break;
            }
        }

        if (todayMenu == null && !fullMenu.isEmpty()) {
            todayMenu = fullMenu.get(0);
        }

        if (todayMenu != null) {
            textViewTodayDay.setText(getDayName(todayMenu.getDayOfWeek()));
            textViewTodaySoup.setText("Soup: " + todayMenu.getSoup());
            textViewTodayMeat.setText("Meat dish: " + todayMenu.getMeatDish());
            textViewTodayFish.setText("Fish dish: " + todayMenu.getFishDish());
            textViewTodayDessert.setText("Dessert: " + todayMenu.getDessert());
        }

        otherDaysMenu.clear();
        for (MenuDay m : fullMenu) {
            if (todayMenu == null || m.getDayOfWeek() != todayMenu.getDayOfWeek()) {
                otherDaysMenu.add(m);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private int getCsvDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int dow = calendar.get(Calendar.DAY_OF_WEEK); // 1..7

        if (dow == Calendar.SUNDAY) {
            return 2; // map Sunday to Monday
        }
        return dow;
    }

    String getDayName(int day) {
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
}
