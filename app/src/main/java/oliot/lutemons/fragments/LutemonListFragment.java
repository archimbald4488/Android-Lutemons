package oliot.lutemons.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import oliot.lutemons.R;
import oliot.lutemons.adapters.LutemonAdapter;
import oliot.lutemons.models.Lutemon;
import oliot.lutemons.storage.Storage;
import java.util.ArrayList;

public class LutemonListFragment extends Fragment {

    private RecyclerView recyclerView;
    private LutemonAdapter lutemonAdapter;
    private ArrayList<Lutemon> lutemonList;
    private Button btnCreateLutemon;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lutemon_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("LutemonListFragment", "Fragment loaded successfully");

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewLutemons);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the list and adapter
        lutemonList = new ArrayList<>(Storage.getInstance().getHomeLutemons().values());
        lutemonAdapter = new LutemonAdapter(lutemonList);
        recyclerView.setAdapter(lutemonAdapter);

        // Button for creating a new Lutemon
        btnCreateLutemon = view.findViewById(R.id.btnCreateLutemon);
        btnCreateLutemon.setOnClickListener(v -> {
            // Get the NavController and navigate to CreateLutemonFragment
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_lutemonListFragment_to_createLutemonFragment);
        });
    }

    // This method is called when the user creates a new Lutemon
    public void loadLutemons() {
        lutemonList.clear();  // Clear the current list
        lutemonList.addAll(Storage.getInstance().getHomeLutemons().values());  // Reload Lutemons from Storage
        lutemonAdapter.notifyDataSetChanged();  // Notify the adapter to refresh the list
    }


    @Override
    public void onResume() {
        super.onResume();
        loadLutemons(); // Reload Lutemons every time the fragment is resumed (e.g., after creating a new Lutemon)
    }
}
