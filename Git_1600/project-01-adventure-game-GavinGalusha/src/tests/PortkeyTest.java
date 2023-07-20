
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//import adventure_game.*;
import adventure_game.items.*;
import adventure_game.NPC;


import org.junit.jupiter.api.BeforeEach;


public class PortkeyTest {
    NPC james;
    Portkey portkey;


    @BeforeEach
    void setup(){
        portkey = new Portkey();
        james = new NPC("NPC", 10, 10, 10);
    }
    



    @Test
    public void testGetName(){
        assertTrue(portkey.getName() == "Portkey");
    }
}
