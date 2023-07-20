package adventure_game.items;

import adventure_game.Character;
import adventure_game.Game;

/** HealingPotion is a consumable that increases a characters health
 * 
 * The increase in health is based on 4 random values between 1 and 4, and then an additional 4.
 * 
 * 
 * If health would increase past max health during healing, it caps out at max instead
 * 
 */
public class HealingPotion implements Consumable {

     /** 
         * Print out healing amount, followed by total health out of max health
         * 
         */ 
    public void consume(Character owner){
        int hitPoints = calculateHealing();
        int hitPointsFromMax = owner.getMaxHealth() - owner.getHealth();

        if(hitPoints > hitPointsFromMax){
            hitPoints = hitPointsFromMax;
        }
        owner.modifyHealth(hitPoints);
        System.out.printf("You heal for %d points, back up to %d/%d.\n", hitPoints, owner.getHealth(), owner.getMaxHealth());
        
       
    }

    private int calculateHealing(){
        // Equivalent to rolling 4d4 + 4
        // sum up four random values in the range [1,4] and
        // add 4 to that.
        int points = Game.rand.nextInt(4)+1;
        points += Game.rand.nextInt(4)+1;
        points += Game.rand.nextInt(4)+1;
        points += Game.rand.nextInt(4)+1;
        return points + 4;


    }

    public String getName(){
        return "Healing Potion";
    }


}
