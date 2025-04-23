package oliot.lutemons.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import oliot.lutemons.R;
import oliot.lutemons.adapters.LutemonAdapter;
import oliot.lutemons.models.Lutemon;
import oliot.lutemons.storage.Storage;
import oliot.lutemons.helpers.RecyclerItemClickListener;

public class TrainingFragment extends Fragment {

    private RecyclerView recyclerView;

    public TrainingFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewTraining);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Lutemon> lutemons = new ArrayList<>(Storage.getInstance().getTrainingLutemons().values());

        LutemonAdapter adapter = new LutemonAdapter(lutemons);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Lutemon lutemon = lutemons.get(position);
                        lutemon.trainLutemon();
                        // lutemon.getTrainings(); consider making a setter

                        Toast.makeText(getContext(), lutemon.getName() + " trained!", Toast.LENGTH_SHORT).show();
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // Optional: add a detailed view or long-press actions
                    }
                }
        ));

        return view;
    }
}
