package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



import adventure_game.*;

public class GameTests {
 
    private Room r;
    private Room p;
    private Game game;

    @BeforeEach
    void setup(){
    
    r = new Room(0, "First Room", "Description");
    p = new Room(0, "Second Room", "Description");
    r.setEast(p);
    p.setWest(r);
    game = new Game();
    }

    @Test
    void testEditStats(){
        assertTrue(game.editStats(5, 10) == 5);
    }

    @Test
    void testIsStatValid(){
        assertTrue(game.isStatValid(5, 10) == true);
    }
}
