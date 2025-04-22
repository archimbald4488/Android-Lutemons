package oliot.lutemons.adapters;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.ViewHolder> {
    // Will implement later

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(android.view.View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { return null; }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {}

    @Override
    public int getItemCount() { return 0; }
}
