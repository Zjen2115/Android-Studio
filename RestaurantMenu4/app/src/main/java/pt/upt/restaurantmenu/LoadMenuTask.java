package pt.upt.restaurantmenu;


import android.os.Handler;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class LoadMenuTask {

    ExecutorService executor;
    Handler resultHandler;
    MainActivity mainActivity;
    String url;
    ArrayList<MenuDay> menuList;

    public LoadMenuTask(ExecutorService executor, Handler resultHandler, MainActivity mainActivity, String url) {
        this.executor = executor;
        this.resultHandler = resultHandler;
        this.mainActivity = mainActivity;
        this.url = url;
        this.menuList = new ArrayList<>();

        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                doWork();
                updateUI();
            }
        });
    }

    private void doWork() {
        HttpHandler handler = new HttpHandler();
        String csv = handler.readInfo(url);

        if (csv != null) {
            String[] lines = csv.split("\\r?\\n");

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].trim();
                if (line.isEmpty()) continue;

                String[] fields = line.split(",");

                if (fields.length >= 5) {
                    try {
                        int day = Integer.parseInt(fields[0].trim());
                        String soup = fields[1].trim();
                        String meat = fields[2].trim();
                        String fish = fields[3].trim();
                        String dessert = fields[4].trim();

                        menuList.add(new MenuDay(day, soup, meat, fish, dessert));
                    } catch (NumberFormatException e) {
                        // skip bad line
                    }
                }
            }
        }
    }

    private void updateUI() {
        resultHandler.post(new Runnable() {
            @Override
            public void run() {
                mainActivity.updateMenu(menuList);
            }
        });
    }
}

