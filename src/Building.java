public abstract class Building extends Card {
    protected int hp;
    protected int damage;
    protected double hitSpeed;
    protected int target; // 0: Ground, 1: Air, 2: Both, 3: Buildings
    protected double range; // 0: Melee
    protected int lifetime;
}
