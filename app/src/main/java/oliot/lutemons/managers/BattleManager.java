package oliot.lutemons.managers;

import oliot.lutemons.models.Lutemon;

public class BattleManager {
    public void startBattle(Lutemon a, Lutemon b) {}
    public void attack(Lutemon attacker, Lutemon defender) {}
    public boolean isBattleOver(Lutemon a, Lutemon b) { return false; }
    public Lutemon getWinner(Lutemon a, Lutemon b) { return null; }
    public void awardExperience(Lutemon winner) {}
    public void updateStats(Lutemon winner, Lutemon loser) {}
}
