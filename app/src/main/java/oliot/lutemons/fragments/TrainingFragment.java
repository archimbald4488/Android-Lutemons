package oliot.lutemons.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import oliot.lutemons.R;
import oliot.lutemons.adapters.TrainingAdapter;
import oliot.lutemons.models.Lutemon;
import oliot.lutemons.storage.Storage;

public class TrainingFragment extends Fragment {

    private RecyclerView recyclerView;
    private TrainingAdapter adapter;
    private TextView noLutemonsMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewTraining);
        noLutemonsMessage = view.findViewById(R.id.noLutemonsMessage);  // Assuming you have this TextView in your layout.

        // Retrieve the list of Lutemons that are being trained
        List<Lutemon> trainingLutemons = new ArrayList<>(Storage.getInstance().getTrainingLutemons().values());

        if (trainingLutemons.isEmpty()) {
            // Show a message if no Lutemons are available for training
            recyclerView.setVisibility(View.GONE);
            noLutemonsMessage.setVisibility(View.VISIBLE);  // Make sure this TextView is properly defined in the layout.
        } else {
            // Show the RecyclerView if Lutemons are available
            recyclerView.setVisibility(View.VISIBLE);
            noLutemonsMessage.setVisibility(View.GONE);

            // Set up the adapter for the RecyclerView
            adapter = new TrainingAdapter(trainingLutemons);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }

        return view;
    }
}
