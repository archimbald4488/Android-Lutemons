package oliot.lutemons.fragments;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

import oliot.lutemons.R;
import oliot.lutemons.managers.BattleManager;
import oliot.lutemons.managers.WeatherManager;
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
    private TextView weatherToday;

    private TextView gameWinner;

    private ImageView playerLutemonImage, enemyLutemonImage, battleIcon;
    private BattleManager battleManager;
    private WeatherManager weather;
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
        playerLutemonImage = view.findViewById(R.id.playerLutemon);
        enemyLutemonImage = view.findViewById(R.id.enemyLutemonImage);
        battleManager = new BattleManager();
        enemyLutemonImage.setImageResource(enemyLutemon.getImageId());
        playerLutemonImage.setImageResource(playerLutemon.getImageId());
        battleIcon = view.findViewById(R.id.attackIcon);
        weather = new WeatherManager();
        weatherToday = view.findViewById(R.id.textView2);


        // Lets get the weather
        HashMap<Integer, String> weatherHashMap = weather.getRandomWeatherCondition();
        int attack = playerLutemon.getAttack(); // We use later to reset the attack back to normal.
        String resultOfWeatherCondition = playerLutemon.weatherCondition(weatherHashMap);
        weatherToday.setText(resultOfWeatherCondition);
        if (playerLutemon != null && enemyLutemon != null) {
            updateUI();

            btnAttack.setOnClickListener(v -> {
                expAward += battleManager.playerAttack(playerLutemon, enemyLutemon);
                playerOutcome = battleManager.getAttackOutcome();
                attackAnimation(playerLutemonImage, enemyLutemonImage);
                playSounds(R.raw.whoosh_vortex_attack);

                if (!battleManager.isBattleOver(playerLutemon, enemyLutemon)) {
                    battleManager.enemyAttack(enemyLutemon, playerLutemon);
                    attackAnimation(enemyLutemonImage, playerLutemonImage);
                    enemyOutocome = battleManager.getAttackOutcome();
                }

                battleIcon.setVisibility(View.VISIBLE);
                battleIcon.setX(playerLutemonImage.getX()+400);
                battleIcon.setY(enemyLutemonImage.getY() - playerLutemonImage.getY()+200);

                new Handler().postDelayed(() -> battleIcon.setVisibility(View.GONE), 500);

                if (battleManager.isBattleOver(playerLutemon, enemyLutemon)) {
                    playerLutemon.resetAttack(attack);
                    battleManager.checkBattleOutcome(playerLutemon, enemyLutemon, expAward);

                    winner = battleManager.getWinner();

                    playSounds(R.raw.battle_end);

                    // Ensure UI updates on the main thread
                    getActivity().runOnUiThread(() -> {
                        gameWinner.setText(winner);
                        btnAttack.setEnabled(false);
                    });

                    // Move Lutemon back to Home and go back to the previous fragment after a delay
                    Storage.getInstance().moveToHome(playerLutemon.getId());

                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        requireActivity().getSupportFragmentManager().popBackStack();
                    }, 4000);
                }

                updateUI(); // update UI  after every action
            });

            btnRun.setOnClickListener(v -> {
                playSounds(R.raw.run_away);
                playerRunsaway.setText("You decide to run away!");
                Storage.getInstance().moveToHome(playerLutemon.getId());
                new android.os.Handler().postDelayed(() ->
                        requireActivity().getSupportFragmentManager().popBackStack(), 4000
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

    // Final functionality BATTLE EFFECTS!!!!
    public void attackAnimation(ImageView attacker, ImageView defnder) {
        float positionX = defnder.getX() - attacker.getX();

        ObjectAnimator animateAttack = ObjectAnimator.ofFloat(attacker, "translationX", 0f, positionX);
        animateAttack.setDuration(500);
        animateAttack.start();



        animateAttack.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator returnAnimation) {
                ObjectAnimator animateReturn = ObjectAnimator.ofFloat(attacker, "translationX", positionX, 0f);
                animateReturn.setDuration(500);
                animateReturn.start();
            }
        });


    }

    public void playSounds(int rawResId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), rawResId);
        mediaPlayer.start();
    }
}