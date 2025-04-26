package oliot.lutemons.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import oliot.lutemons.R;
import oliot.lutemons.adapters.LutemonAdapter;
import oliot.lutemons.storage.Storage;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private LutemonAdapter adapter;
    private Button saveButton, loadButton;
    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new LutemonAdapter(new ArrayList<>(Storage.getInstance().getHomeLutemons().values()));
        recyclerView.setAdapter(adapter);

        saveButton = view.findViewById(R.id.saveLutemons);
        saveButton.setOnClickListener(v -> {
            Storage.getInstance().saveCurrentLutemons(getContext());
        });

        loadButton = view.findViewById(R.id.loadLutemons);
        loadButton.setOnClickListener(v -> {
            Storage.getInstance().loadSavedLutemons(getContext());
            adapter.updateLutemons(new ArrayList<>(Storage.getInstance().getHomeLutemons().values()));
        });

        return view;




    }



}
