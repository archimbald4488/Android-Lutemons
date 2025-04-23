package oliot.lutemons.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import oliot.lutemons.R;
import oliot.lutemons.models.Lutemon;
import oliot.lutemons.storage.Storage;

public class LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.ViewHolder> {
    private ArrayList<Lutemon> lutemons;

    public LutemonAdapter(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameView, statsView;
        ImageView imageView;
        Button trainButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.lutemon_name);
            statsView = itemView.findViewById(R.id.lutemon_stats);
            imageView = itemView.findViewById(R.id.lutemon_image);
            trainButton = itemView.findViewById(R.id.btn_send_to_training);
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
        //holder.imageView.setImageResource(lutemon.getImageId()); uncomment after adding real images

        holder.trainButton.setOnClickListener(v -> {
            Storage.getInstance().moveToTraining(lutemon.getId());
            lutemons.remove(position);  // remove from current view if needed
            notifyItemRemoved(position);
            Toast.makeText(holder.itemView.getContext(), lutemon.getName() + " sent to training!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
