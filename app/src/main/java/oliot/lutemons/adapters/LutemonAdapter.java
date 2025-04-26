package oliot.lutemons.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import oliot.lutemons.R;
import oliot.lutemons.fragments.BattleFragment;
import oliot.lutemons.managers.BattleManager;
import oliot.lutemons.models.Lutemon;
import oliot.lutemons.storage.EnemyStorage;
import oliot.lutemons.storage.Storage;

public class LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.ViewHolder> {
    private final ArrayList<Lutemon> lutemons;

    public LutemonAdapter(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameView, statsView;
        ImageView imageView;
        Button trainButton;
        Button battleButton;
        Button homeButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.lutemon_name);
            statsView = itemView.findViewById(R.id.lutemon_stats);
            imageView = itemView.findViewById(R.id.lutemon_image);
            trainButton = itemView.findViewById(R.id.btn_send_to_training);
            battleButton = itemView.findViewById(R.id.btn_send_to_battle);
            homeButton = itemView.findViewById(R.id.btn_send_to_home);
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
        String lutemonName = lutemon.getName() + " (" + lutemon.getType() + ")";
        holder.nameView.setText(lutemonName);
        String statsText = "Exp: " + lutemon.getExperience() +
                " | HP: " + lutemon.getCurrentHealth() +
                " | ATK: " + lutemon.getAttack() +
                " | DEF: " + lutemon.getDefense();
        holder.statsView.setText(statsText);
        //holder.imageView.setImageResource(lutemon.getImageId()); uncomment after adding real images

        // Set click listener for "Send to Training"
        holder.trainButton.setOnClickListener(v -> {
            Storage.getInstance().moveToTraining(lutemon.getId());
            Toast.makeText(v.getContext(), lutemon.getName() + " sent to training!", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();  // Refresh the RecyclerView to reflect the updated data
        });

        // Set click listener for "Send to Battle"
        holder.battleButton.setOnClickListener(v -> {
            Storage.getInstance().moveToBattlefield(lutemon.getId());
            Toast.makeText(v.getContext(), lutemon.getName() + " sent to battle!", Toast.LENGTH_SHORT).show();

            // Lutemons  as static references
            BattleFragment.playerLutemon = lutemon;
            BattleFragment.enemyLutemon = EnemyStorage.getInstance().getRandomEnemy();

            // goto BattleFragment
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, new BattleFragment()) // ID from activity_main.xml
                    .addToBackStack(null)
                    .commit();
        });


        holder.homeButton.setOnClickListener(v -> {
            // Send Lutemon to home
            Storage.getInstance().moveToHome(lutemon.getId());
            notifyDataSetChanged();  // Update the list
            Toast.makeText(holder.itemView.getContext(), lutemon.getName() + " sent to Home", Toast.LENGTH_SHORT).show();
        });
    }
}


