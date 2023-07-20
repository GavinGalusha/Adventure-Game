package adventure_game.items;
import adventure_game.Character;


public class Portkey implements Consumable{



    /**
     * this function does nothing
     */
    @Override
    public void consume(Character owner) {
        System.out.println("You ate the Porkey");
        
    }

    /**
     * Returns "Portkey"
     */
    public String getName(){
        return "Portkey";
    }



}