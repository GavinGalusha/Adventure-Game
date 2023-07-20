package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import adventure_game.items.*;
import adventure_game.NPC;

import adventure_game.Room;

import org.junit.jupiter.api.BeforeEach;

public class RoomTests{
    Room room;
    Room room2;
    NPC villain;
    RagePotion rage;

    @BeforeEach
    void setup(){
        room = new Room(0, "test room", "Test room description");
        room2 = new Room(0, "test room2", "Test room description 2");
        villain = new NPC("Name", 10, 15, 10);
        rage = new RagePotion();
    }



    @Test
    public void testSetters(){
       
        room.setEast(room2);
        assertTrue(room.getEast() == room2);
        room.setWest(room2);
        assertTrue(room.getWest() == room2);
        room.setNorth(room2);
        assertTrue(room.getNorth() == room2);
        room.setSouth(room2);
        assertTrue(room.getSouth() == room2);
    }

    @Test
    public void opponentTest(){
        room.setOpponent(villain);
        assertTrue(room.getOpponent() == villain);
        assertTrue(room.getNPC() == villain);
        room.removeNPC();
        assertTrue(room.NPCPresent() == false);
    }

    @Test
    public void itemsTest(){
        room.addConsumable(rage);
        assertTrue(room.getItems().get(0) == rage);
        assertTrue(room.itemPresent() == true);
        room.removeItem(rage);
        
        assertTrue(room.getItems().isEmpty() == true);


    }






}