package oliot.lutemons.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import oliot.lutemons.R;
import oliot.lutemons.managers.BattleManager;
import oliot.lutemons.models.Lutemon;
import oliot.lutemons.storage.Storage;

public class BattleFragment extends Fragment {

    public static Lutemon playerLutemon;
    public static Lutemon enemyLutemon;

    private TextView playerNameView;
    private TextView enemyNameView;

    private TextView playerRunsaway;
    private Button btnAttack, btnRun;

    String playerOutcome, enemyOutocome, winner;
    private TextView playerAttackLogView;
    private TextView enemyAttackLogView;

    private TextView gameWinner;


    private BattleManager battleManager;

    int expAward = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_battle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        playerNameView = view.findViewById(R.id.player_name);
        enemyNameView = view.findViewById(R.id.enemy_name);
        btnAttack = view.findViewById(R.id.btn_attack);
        btnRun = view.findViewById(R.id.btn_run);
        playerAttackLogView = view.findViewById(R.id.player_attack_log);
        enemyAttackLogView = view.findViewById(R.id.enemy_attack_log);
        playerRunsaway = view.findViewById(R.id.player_runsaway);
        gameWinner = view.findViewById(R.id.game_winner);


        battleManager = new BattleManager();

        if (playerLutemon != null && enemyLutemon != null) {
            updateUI();

            btnAttack.setOnClickListener(v -> {
                expAward += battleManager.playerAttack(playerLutemon, enemyLutemon);
                playerOutcome = battleManager.getAttackOutcome();

                if (!battleManager.isBattleOver(playerLutemon, enemyLutemon)) {
                    battleManager.enemyAttack(enemyLutemon, playerLutemon);
                    enemyOutocome = battleManager.getAttackOutcome();
                }

                if (battleManager.isBattleOver(playerLutemon, enemyLutemon)) {
                    battleManager.checkBattleOutcome(playerLutemon, enemyLutemon, expAward);
                    winner = battleManager.getWinner();
                    gameWinner.setText(winner);

                    btnAttack.setEnabled(false);
                    // Move Lutemon back to Home
                    Storage.getInstance().moveToHome(playerLutemon.getId());

                    // Return to previous fragment
                    new android.os.Handler().postDelayed(() ->
                            requireActivity().getSupportFragmentManager().popBackStack(), 1500
                    );
                }

                updateUI();
            });

            btnRun.setOnClickListener(v -> {
                playerRunsaway.setText("You decide to run away!");
                Storage.getInstance().moveToHome(playerLutemon.getId());
                new android.os.Handler().postDelayed(() ->
                        requireActivity().getSupportFragmentManager().popBackStack(), 1500
                );
            });
        }
    }

    private void updateUI() {
        String playerStats = "Player: " + playerLutemon.getName() + " | HP: " +
                playerLutemon.getCurrentHealth() + "/" + playerLutemon.getMaxHealth();
        String enemyStats = "Enemy: " + enemyLutemon.getName() + " | HP: " +
                enemyLutemon.getCurrentHealth() + "/" + enemyLutemon.getMaxHealth();

        playerNameView.setText(playerStats);
        enemyNameView.setText(enemyStats);
        playerAttackLogView.setText(playerOutcome);
        enemyAttackLogView.setText(enemyOutocome);

    }
}
