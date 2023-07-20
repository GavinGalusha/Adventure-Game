package adventure_game;
import java.util.ArrayList;


import adventure_game.items.Consumable;

abstract public class Character{
    private int maxHealth;
    private int health;

    private int maxMana;
    private int mana;

    private int baseDamage;

    private String name;

    private ArrayList<Consumable> items;

    // Character Conditions:
    private int turnsVulnerable; // number of turns Character is vulnerable
    private int turnsInvincible; // number of turns Character takes no damage
    private int turnsStunned; // number of turns Character gets no actions

    // buffer factor for next attack
    // E.g, if 2.0, the next attack will do double damage
    private double tempDamageBuff;

    //the currentRoomID variable stores the current room id
    private int currentRoomID = 0;
    private ArrayList<Room> characterRooms;
    //the characterRooms variable stores all rooms that the character can go to. 

    
    /**
     * constructer for character
     * @param name  character name
     * @param health character health
     * @param mana character mana
     * @param damage character damage
     */
    public Character(String name, int health, int mana, int damage){
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.maxMana = mana;
        this.mana = mana;
        this.baseDamage = damage;
        this.tempDamageBuff = 1.0;
        items = new ArrayList<Consumable>();
    }
/**
 * overriding to string so that printing a character is formatted nice
 */
    @Override
    public String toString(){
        String output;
        output = "";
        output += "Name " + getName() + "\n";
        output += "hp " + getHealth() + "\n";
        output += "mana " + getMana() + "\n";
        output += "damage " + getBaseDamage() + "\n";
        return output;
    }

    /**
     * Get the name of this Character
     * @return the name of this Character
     */
    public String getName(){
        return this.name;
    }


    /**
     * Get the health of this Character
     * @return the health of this Character
     */
    public int getHealth(){
        return this.health;
    }
    /**
     * Gets the current room
     * 
     * @return current room player is in
     */
    public Room getRoom(){
        return this.characterRooms.get(this.currentRoomID);
    }
    /**
     * Get the maxHealth of this Character
     * @return the maxHealth of this Character
     */
    public int getMaxHealth(){
        return this.maxHealth;
    }
    /**
     * Get the maxMana of this Character
     * @return the maxMana of this Character
     */

    public int getMaxMana(){
        return this.maxMana;
    }
/**
 * 
 * increases character mana by 1
 */
    public void incrementMana(){
        this.mana++;
    }
/**
     * Get the mana of this Character
     * @return the mana of this Character
     */
    public int getMana(){
        return this.mana;
    }
/**
     * Get the baseDamage of this Character
     * @return the baseDamage of this Character
     */
    public int getBaseDamage(){
        return this.baseDamage;
    }

    /**
     * Check if character is alive
     * @return the true if alive
     */
    public boolean isAlive(){
        return this.health > 0;
    }

    /**
     * Function that enables the character to make decisions
     * @param other the other character
     */
    abstract void takeTurn(Character other);


    /**
     * deals damage to other characters health based on a culmination of factors including invincibility, vulnerability, and chance
     * @param other target of attack
     */
    public void attack(Character other){
        if(other.isInvincible()){
            System.out.printf("%S is unable to attack %S!\n", 
                                this.getName(), 
                                other.getName());
            other.decreaseTurnsInvincible();
            return;
        }
        double modifier = Game.rand.nextDouble();
        modifier = (modifier*0.4) + 0.8;
        int damage = (int)(this.baseDamage * modifier);
        // apply temporary damage buff, then reset it back to 1.0
        damage *= this.tempDamageBuff;
        this.tempDamageBuff = 1.0;

        if(other.isVulnerable()){
            damage *= 1.5;
            other.decreaseTurnsVulnerable();
        }

        System.out.printf("%s dealt %d damage to %s\n", 
                            this.getName(), 
                            damage, 
                            other.getName());
        other.modifyHealth(-damage);
    }

/**
 * 
 * The charge up mana function increases mana by 3
 * 
 */
    public void chargeUpMana(){
        this.mana += 3;
        System.out.println("Mana increased by 3");
    }


    /**
     * This spell reduces the opponent's health by half
     * 
     * 
     * @param other the character you are casting a spell on
     */
    public void castSpell(Character other){
        
        if (this.mana >= 3){
            other.modifyHealth(-(other.getHealth()/2));
            
            System.out.println("The fire spell burns your opponent, reducing their health by half!");
            this.mana -= 3;
            System.out.printf("Mana remaining: %d\n", this.mana);
        }
        else{
            System.out.println("The spell fizzled! You do not have enough mana!\n");
        }
    }



    /**
     * defend has a 75% chance of succeeding, in which case they are invincible and charge up their attack damage
     * the other 25% chance is for them to stumble and be vulnerable for a turn
     * 
     * 
     * @param other other character
     * 
     * 
     */
    public void defend(Character other){
        double chance = Game.rand.nextDouble();
        if(chance <=0.75){
            System.out.printf("%s enters a defensive posture and charges up their next attack!\n", this.getName());
            this.setAsInvincible(1);
            this.setTempDamageBuff(2.0);
        } else {
            System.out.printf("%s stumbles. They are vulnerable for the next turn!\n", this.getName());
            this.setAsVulnerable(1);
        }
    }
    
    /**
     * modify health changes a characters health by the modified amount
     * @param modifier modifier amount to change health
     * 
     * cannot go below 0 or over maxhealth
     */
    public void modifyHealth(int modifier) {
        this.health += modifier;
        if(this.health < 0){
            this.health = 0;
        }
        if(this.health > this.getMaxHealth()){
            this.health = this.getMaxHealth();
        }
    }

    /**
     * modify attack changes how much damage the character can deal by modifying their basedamage
     * 
     * @param modifier the amount that you want to change base damage by
     */
    public void modifyAttack(int modifier){
        this.baseDamage += modifier;

    }
    /* CONDITIONS */

    /**
     * sets number of vulnerable turns
     * @param numTurns the number of turns the character will be vulnerable for 
     */
    public void setAsVulnerable(int numTurns){
        this.turnsVulnerable = numTurns;
    }
/**
 * true if vulnerable, false if not
 * @return true if vulnerable, false if not
 */
    public boolean isVulnerable(){
        return this.turnsVulnerable > 0;
    }

    /**
     *  decrement number of vulnerable turns by 1
     */
    public void decreaseTurnsVulnerable(){
        this.turnsVulnerable--;
    }

    /**
     * Sets invincibility
     * @param numTurns the number of turns of invincibilty 
     */
    public void setAsInvincible(int numTurns){
        this.turnsInvincible = numTurns;
    }

    /**
     * Checks invincibility
     * @return true if currently invincible, false if not
     */
    public boolean isInvincible(){
        return this.turnsInvincible > 0;
    }


    /**
     * decrement invincibility by 1
     */
    public void decreaseTurnsInvincible(){
        this.turnsInvincible--;
    }

    /**
     * Sets character as stunned
     * @param numTurns the number of turns stunned for
     */
    public void setAsStunned(int numTurns){
        this.turnsStunned = numTurns;
    }


    /**
     * Checks stunned status
     * @return if character is stunned or not
     */
    public boolean isStunned(){
        return this.turnsStunned > 0;
    }

    /**
     * decrement the number of turns stunned by 1
     */
    public void decreaseTurnsStunned(){
        this.turnsStunned--;
    }

    /**
     * Set the temporary damage buffer. 
     * 
     * This is a multiplicative factor which will modify the damage 
     * for the next attack made by this Character. After the next 
     * attack, it will get reset back to 1.0
     * 
     * @param buff the multiplicative factor for the next attack's
     * damage.
     */
    public void setTempDamageBuff(double buff){
        this.tempDamageBuff = buff;
    }


    /**
     * gets the damage buff modifiers
     * 
     * @return the current damage buff of the character
     */
    public double getTempDamageBuff(){
        return this.tempDamageBuff;
    }
/**
 * 
 * 
 * obtain item
 * 
 * @param item the consumable that is added to items
 */
    public void obtain(Consumable item){
        items.add(item);
    }

    /**
     * Use an item
     * @param owner character to consume the item
     * @param other can be used if the item effects a second character
     */
    public void useItem(Character owner, Character other){
        int i = 1;
        System.out.printf("  Do you want to use:\n");
        for(Consumable item : items){
            System.out.printf("    %d: %S\n", i, item.getName());
            i++;
        }
        System.out.print("  Enter your choice: ");
        int choice = Game.in.nextInt();
        items.get(choice-1).consume(owner);
        items.remove(choice-1);
    }


    /**
     * checks if character has items
     * @return returns false if character has no item
     */
    public boolean hasItems(){
        return !items.isEmpty();
    }


    /**
     * sets all rooms that this character can enter
     * @param list the list of rooms that character will be able to enter
     */
    public void setRooms(ArrayList<Room> list){
        this.characterRooms = list;
    }

    


  

}