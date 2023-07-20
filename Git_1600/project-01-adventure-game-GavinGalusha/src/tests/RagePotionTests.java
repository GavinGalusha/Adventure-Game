package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import adventure_game.items.*;
import adventure_game.Player;

import org.junit.jupiter.api.BeforeEach;


public class RagePotionTests { 

    private Player testPlayer;
    private RagePotion testPotion;

 @BeforeEach
        void setup() {
            testPlayer = new Player("TestPlayer", 100, 10, 10);
            testPotion = new RagePotion(); 
        }


        @Test
        void testRagePotion() {
            testPotion.consume(testPlayer);
            assertTrue(testPlayer.getTempDamageBuff() == 4);


        }








}