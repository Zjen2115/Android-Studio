package pt.upt.restaurantmenu;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> implements View.OnClickListener {

    ArrayList<MenuDay> days;

    public MenuAdapter(ArrayList<MenuDay> days) {
        this.days = days;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_menu_day, parent, false);

        view.setOnClickListener(this);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuDay menu = days.get(position);

        holder.textViewDay.setText(getDayName(menu.getDayOfWeek()));
        holder.textViewSummary.setText("Soup: " + menu.getSoup());

        holder.itemView.setTag(menu);
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
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

    @Override
    public void onClick(View v) {
        MenuDay menu = (MenuDay) v.getTag();
        if (menu == null) return;

        Intent intent = new Intent(v.getContext(), ShowMenuActivity.class);
        intent.putExtra(ShowMenuActivity.KEY_DAY, menu.getDayOfWeek());
        intent.putExtra(ShowMenuActivity.KEY_SOUP, menu.getSoup());
        intent.putExtra(ShowMenuActivity.KEY_MEAT, menu.getMeatDish());
        intent.putExtra(ShowMenuActivity.KEY_FISH, menu.getFishDish());
        intent.putExtra(ShowMenuActivity.KEY_DESSERT, menu.getDessert());
        v.getContext().startActivity(intent);
    }
}

