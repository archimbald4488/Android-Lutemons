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
import oliot.lutemons.adapters.LutemonAdapter;
import oliot.lutemons.storage.Storage;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private LutemonAdapter adapter;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new LutemonAdapter(new ArrayList<>(Storage.getInstance().getHomeLutemons().values()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
