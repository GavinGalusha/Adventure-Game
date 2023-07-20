package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//import adventure_game.*;
import adventure_game.items.*;
import adventure_game.Player;

import org.junit.jupiter.api.BeforeEach;






public class HealingPotionTests {
    private Player testPlayer;
    private HealingPotion testPotion;


        @BeforeEach
        void setup() {
            testPlayer = new Player("TestPlayer", 100, 10, 10);
            testPotion = new HealingPotion(); 
        }
     
        @Test
        void testMaxHealthLimitation() {
            testPotion = new HealingPotion();
            
            assertTrue(testPlayer.getHealth() == 100); 
            testPotion.consume(testPlayer);
            assertTrue(testPlayer.getHealth() == 100); 
        }
    
        @Test
        void testHealingPotionWorks() {
            
            assertTrue(testPlayer.getHealth() == 100);
            testPlayer.modifyHealth(-40); 
            testPotion.consume(testPlayer); 
            assertTrue(testPlayer.getHealth() > 60); 
        }

        



        }




    
        

        

       
    

