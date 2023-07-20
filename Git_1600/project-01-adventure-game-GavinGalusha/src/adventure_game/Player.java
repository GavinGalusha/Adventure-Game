package adventure_game;



public class Player extends Character{
    /**
     * constructor
     * @param name name
     * @param health health
     * @param mana mana
     * @param baseDamage baseDamage
     */
    public Player(String name, int health, int mana, int baseDamage){
        super(name, health, mana, baseDamage);
    }

    
    /**
     * Function to take a turn
     */
    @Override
    public void takeTurn(Character other){
        if(this.isStunned()){
            this.decreaseTurnsStunned();
            System.out.printf("%S is unable to take any actions this turn!", this.getName());
            return;
        }
        
    

        System.out.println();
        System.out.printf("%s has %d of %d health.\n", this.getName(), this.getHealth(), this.getMaxHealth());
        System.out.printf("%s has %d health.\n", other.getName(), other.getHealth());
        System.out.printf("Do you want to...\n");
        System.out.printf("  1: Attack?\n");
        System.out.printf("  2: Defend?\n");
        System.out.printf("  3: Cast a Spell?\n");
        System.out.printf("  4: Charge up mana?\n");
        if(this.hasItems())
            System.out.printf("  5: Use an item?\n");
        System.out.printf("Enter your choice: ");

        int choice = Game.in.nextInt();

        switch(choice){
            case 1:
                System.out.println();
                this.attack(other);
                break;
            case 2:
                System.out.println();
                this.defend(other);
                break;
            case 3: 
                System.out.println();
                this.castSpell(other);
                break;
            case 4:
                System.out.println();
                this.chargeUpMana();
                break;
            case 5:
                System.out.println();
                if(hasItems()){
                    this.useItem(this, other);
                } else {
                    System.out.println("You dig through your bag but find no items. You lose a turn!!");
                }
                break;
            
        }
    }
}