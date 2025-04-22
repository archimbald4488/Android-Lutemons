package oliot.lutemons.models;

public abstract class Lutemon {
    protected String name, color;
    protected int attack, defense, maxHealth, currentHealth, experience;
    protected int battles, wins, trainings;

    public abstract void train();
    public abstract void resetHealth();
    public abstract int getTotalAttack();
    public abstract boolean isAlive();
    public abstract void receiveDamage(int damage);
}
