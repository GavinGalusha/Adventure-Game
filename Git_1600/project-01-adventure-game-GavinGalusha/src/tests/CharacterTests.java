package tests;
//package tests;
//package adventure_game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import adventure_game.Character;
import adventure_game.Player;
import adventure_game.NPC;

public class CharacterTests{

    private Character c;
    private Character a;

    @BeforeEach
    void setup(){
        c = new Player("TestPlayer", 100, 10, 5);
        a = new NPC("Non Playable Character" , 100, 10, 5);
    }

    @Test
    void testModifyHealth(){
        assertTrue(c.getHealth() == 100);
        c.modifyHealth(-10);
        assertTrue(c.getHealth() == 90);
    }

    @Test
    void testAttack(){

        
        a.setAsInvincible(1); 
        c.attack(a);
        assertTrue(a.getHealth() == 100);
        assertFalse(a.isInvincible());

       
        a.setAsVulnerable(1);
        c.attack(a);
        int health = a.getHealth();
        assertTrue(health < 100);
        assertFalse(a.isVulnerable()); 
        a.modifyHealth(1000); 

        
        c.attack(a);
        assertTrue(a.getHealth() < 100);
        assertTrue(a.getHealth() > health); 

     }

   
     @Test
     void testSetAsVulnerable(){
        c.setAsVulnerable(1);
        assertTrue(c.isVulnerable());
     }


     @Test
     void testDecreaseTurnsVulnerable(){
        c.setAsVulnerable(2);
        c.decreaseTurnsVulnerable();
        assertTrue(c.isVulnerable());
        c.decreaseTurnsVulnerable();
        assertFalse(c.isVulnerable());
     }

   
     @Test
     void testSetAsInvincible(){
        c.setAsInvincible(1);
        assertTrue(c.isInvincible());
     }

     @Test
     void testDecreaseTurnsInvincible(){
        c.setAsInvincible(2);
        c.decreaseTurnsInvincible();
        assertTrue(c.isInvincible());
        c.decreaseTurnsInvincible();
        assertFalse(c.isInvincible());
     }
   
    @Test
    void testSetAsStunned(){
       c.setAsStunned(1);
       assertTrue(c.isStunned());
    }

    @Test
    void testDecreaseTurnsStunned(){
       c.setAsStunned(2);
       c.decreaseTurnsStunned();
       assertTrue(c.isStunned());
       c.decreaseTurnsStunned();
       assertFalse(c.isStunned());
    }

    @Test
    void testCastSpell(){
      c.castSpell(a);
      assertTrue(a.getHealth() == 50);
    }

    @Test
    void testModifyAttack(){
      c.modifyAttack(5);
      assertTrue(c.getBaseDamage() == 10);
    }
   
    @Test
    void testChargeUpMana(){
      c.chargeUpMana();
      assertTrue(c.getMana() == 13);
    }

    @Test
    void testIncrementMana(){
      c.incrementMana();
      assertTrue(c.getMana() == 11);

    }



    }

    
