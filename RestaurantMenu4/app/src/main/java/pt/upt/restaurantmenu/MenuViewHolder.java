package pt.upt.restaurantmenu;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    TextView textViewDay;
    TextView textViewSummary;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewDay = itemView.findViewById(R.id.textViewDayRow);
        textViewSummary = itemView.findViewById(R.id.textViewSummaryRow);
    }
}
