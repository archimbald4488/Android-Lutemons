package oliot.lutemons.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import oliot.lutemons.R;
import oliot.lutemons.adapters.StatsAdapter;
import oliot.lutemons.storage.Storage;
import oliot.lutemons.models.Lutemon;

public class StatsFragment extends Fragment {
    private RecyclerView statsRecyclerView;
    private StatsAdapter statsAdapter;
    private ArrayList<Lutemon> lutemons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stats, container, false);


        statsRecyclerView = view.findViewById(R.id.lutemonRecyclerView);
        statsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get all Lutemons from Storage
        lutemons = new ArrayList<>(Storage.getInstance().getHomeLutemons().values());
        statsAdapter = new StatsAdapter(lutemons);


        statsRecyclerView.setAdapter(statsAdapter);

        return view;
    }
}