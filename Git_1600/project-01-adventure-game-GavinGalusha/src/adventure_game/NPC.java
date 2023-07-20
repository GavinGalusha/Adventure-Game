package adventure_game;
public class NPC extends Character{

    /**
     * constructor for NPC
     * 
     * 
     * @param name character name
     * @param health character health
     * @param mana character mana
     * @param baseDamage character basedamage
     */
    public NPC(String name, int health, int mana, int baseDamage){
        super(name, health, mana, baseDamage);
    }
    
    /**
     * 
     * slightly modify's taketurn function, all that the NPC can do is attack
     */
    @Override
    public void takeTurn(Character other){
        if(this.isStunned()){
            this.decreaseTurnsStunned();
            System.out.printf("%S is unable to take any actions this turn!", this.getName());
            return;
        }
        this.attack(other);
    }
}