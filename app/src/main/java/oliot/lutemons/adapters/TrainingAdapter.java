package oliot.lutemons.adapters;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oliot.lutemons.R;
import oliot.lutemons.models.Lutemon;
import oliot.lutemons.storage.Storage;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {

    private final List<Lutemon> lutemons;
    private final Map<Integer, CountDownTimer> timers = new HashMap<>();
    private final Map<Integer, Long> remainingTime = new HashMap<>();

    public TrainingAdapter(List<Lutemon> lutemons) {
        this.lutemons = lutemons;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameView, countdownView;
        Button trainButton;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.lutemon_image_in_training);
            nameView = itemView.findViewById(R.id.lutemon_name_in_training);
            countdownView = itemView.findViewById(R.id.countdown_view);
            trainButton = itemView.findViewById(R.id.train_button);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.training_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Check if the position is valid
        if (position >= lutemons.size()) return;

        Lutemon lutemon = lutemons.get(position);

        holder.imageView.setImageResource(lutemon.getImageId());
        holder.nameView.setText(lutemon.getName());


        if (timers.containsKey(lutemon.getId())) {
            long timeLeft = remainingTime.getOrDefault(lutemon.getId(), 10000L);
            holder.countdownView.setText("Training: " + (timeLeft / 1000) + "s");
            holder.trainButton.setEnabled(false);
        } else {
            holder.countdownView.setText("Training: 10s");
            holder.trainButton.setEnabled(true);
        }

        holder.trainButton.setOnClickListener(v -> {

            if (!timers.containsKey(lutemon.getId())) {
                startTraining(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    private void startTraining(int position) {
        if (position >= lutemons.size()) return; // Prevent accessing out of bounds

        Lutemon lutemon = lutemons.get(position);
        long totalDuration = 10000; // 10 seconds

        // Create a  timer for the Lutemon
        CountDownTimer timer = new CountDownTimer(totalDuration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime.put(lutemon.getId(), millisUntilFinished);
                notifyItemChanged(position);
            }

            @Override
            public void onFinish() {

                lutemon.addExperience(10);
                Storage.getInstance().moveToHome(lutemon.getId());


                timers.remove(lutemon.getId());
                remainingTime.remove(lutemon.getId());


                if (lutemons.size() > position) {
                    lutemons.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, lutemons.size());
                } else {
                    lutemons.clear(); // Force a complete refresh of the adapter. Other wise for some reason the last item just stays there.

                    notifyDataSetChanged();
                }
            }
        };

        // Store the timer and remaining time for this Lutemon
        timers.put(lutemon.getId(), timer);
        remainingTime.put(lutemon.getId(), totalDuration);
        timer.start();

        // Update UI for this Lutemon
        notifyItemChanged(position);
    }
}
