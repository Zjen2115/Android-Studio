package pt.upt.trabalha2_studentgrades;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private ArrayList<String> line1List;
    private ArrayList<String> line2List;
    private OnItemClickListener listener;

    public MyAdapter(ArrayList<String> line1List,
                     ArrayList<String> line2List,
                     OnItemClickListener listener) {
        this.line1List = line1List;
        this.line2List = line2List;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textLine1.setText(line1List.get(position));
        holder.textLine2.setText(line2List.get(position));
    }

    @Override
    public int getItemCount() {
        return line1List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textLine1;
        TextView textLine2;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textLine1 = itemView.findViewById(R.id.textLine1);
            textLine2 = itemView.findViewById(R.id.textLine2);

            if (listener != null) {
                itemView.setOnClickListener(v -> {
                    int pos = getBindingAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        listener.onItemClick(pos);
                    }
                });
            }
        }
    }
}
