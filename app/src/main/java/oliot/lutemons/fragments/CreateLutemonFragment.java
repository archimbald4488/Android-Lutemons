package oliot.lutemons.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import oliot.lutemons.R;
import oliot.lutemons.models.Lutemon;
import oliot.lutemons.models.*;
import oliot.lutemons.storage.Storage;

public class CreateLutemonFragment extends Fragment {

    private EditText editName;
    private RadioGroup radioGroupColor;
    private Button btnCreate;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_lutemon, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editName = view.findViewById(R.id.editTextName);
        radioGroupColor = view.findViewById(R.id.radioGroupColor);
        btnCreate = view.findViewById(R.id.btnCreateLutemon);

        btnCreate.setOnClickListener(v -> {
            String name = editName.getText().toString();
            int selectedId = radioGroupColor.getCheckedRadioButtonId();

            if (name.isEmpty() || selectedId == -1) {
                Toast.makeText(getActivity(), "Name and color are required.", Toast.LENGTH_SHORT).show();
                return;
            }

            Lutemon newLutemon = null;

            if (selectedId == R.id.radioWhite) {
                newLutemon = new WhiteLutemon(name);
            } else if (selectedId == R.id.radioGreen) {
                newLutemon = new GreenLutemon(name);
            } else if (selectedId == R.id.radioPink) {
                newLutemon = new PinkLutemon(name);
            } else if (selectedId == R.id.radioOrange) {
                newLutemon = new OrangeLutemon(name);
            } else if (selectedId == R.id.radioBlack) {
                newLutemon = new BlackLutemon(name);
            }

            if (newLutemon != null) {
                Storage.getInstance().addLutemon(newLutemon);
                Storage.getInstance().moveToHome(newLutemon.getId());
                Toast.makeText(getActivity(), name + " created!", Toast.LENGTH_SHORT).show();
                editName.setText("");
                radioGroupColor.clearCheck();
            }
        });
    }
}
