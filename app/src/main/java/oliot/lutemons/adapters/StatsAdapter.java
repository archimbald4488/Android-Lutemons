package oliot.lutemons.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import oliot.lutemons.R;
import oliot.lutemons.models.Lutemon;

// Stats fragment has its own adapter since I didn't want to alter the adapter for the other parts of the code
// And the text parts doesn't use exactly the same values in the other adapter
// Sure might've been cleaner to have only one adapter but this works just fine.
public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.ViewHolder> {
    private final ArrayList<Lutemon> lutemons;

    public StatsAdapter(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public StatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lutemon_stat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);

        // This is not the same as the one in the OG adapter and why I decided to get a new one
        holder.nameView.setText(lutemon.getName() + " (" + lutemon.getType() + ")");
        String statsText = "EXP: " + lutemon.getExperience() +
                " | Wins: " + lutemon.getWins() +
                " | Battles: " + lutemon.getBattles();
        holder.statsView.setText(statsText);

        // Values all set at 100... I mean don't know if it should be lower for Wins and Battles
        // I maybe could be lower because I doubt anyone will play more than a few games.
        // You can lower it.
        holder.expProgressBar.setMax(1500); // or dynamic max
        holder.expProgressBar.setProgress(lutemon.getExperience());

        holder.winsProgressBar.setMax(25);
        holder.winsProgressBar.setProgress(lutemon.getWins());

        holder.battlesProgressBar.setMax(25);
        holder.battlesProgressBar.setProgress(lutemon.getBattles());
        holder.lutemonImage.setImageResource(lutemon.getImageId());

    }


    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameView, statsView;
        ProgressBar expProgressBar, winsProgressBar, battlesProgressBar;
        ImageView lutemonImage;
        public ViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.lutemon_name);
            statsView = itemView.findViewById(R.id.lutemon_stats);
            expProgressBar = itemView.findViewById(R.id.progress_exp);
            winsProgressBar = itemView.findViewById(R.id.progress_wins);
            battlesProgressBar = itemView.findViewById(R.id.progress_battles);
            lutemonImage = itemView.findViewById(R.id.lutemon_image_stats);

        }
    }
}
