package adventure_game.items;
import adventure_game.Character;


public class RagePotion implements Consumable{

    /**
     * buffs damage by 4
     */
    @Override
    public void consume(Character owner){

        System.out.println("MULTIPLYING ATTACK POWER BY 4 FOR NEXT TURN!!!");
        owner.setTempDamageBuff(4);
        
    }
    
    /**
     * returns name
     */
    public String getName(){
        return "Rage Potion";
    }


}
