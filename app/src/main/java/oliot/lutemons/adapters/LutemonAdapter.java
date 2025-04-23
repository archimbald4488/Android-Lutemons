package oliot.lutemons.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import oliot.lutemons.R;
import oliot.lutemons.models.Lutemon;

public class LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.ViewHolder> {
    private ArrayList<Lutemon> lutemons;

    public LutemonAdapter(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameView, statsView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.lutemon_name);
            statsView = itemView.findViewById(R.id.lutemon_stats);
            imageView = itemView.findViewById(R.id.lutemon_image);
        }
    }

    @Override
    public LutemonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lutemon_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.nameView.setText(lutemon.getName());
        holder.statsView.setText("HP: " + lutemon.getCurrentHealth());
        //holder.imageView.setImageResource(lutemon.getImageId()); ADD IMAGES TO LUTEMONS FIRST
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
