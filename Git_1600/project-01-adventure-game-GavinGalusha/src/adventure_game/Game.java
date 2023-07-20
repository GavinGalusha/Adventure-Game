package adventure_game;

/*
 * Project-01: Adventure Game
 * Name:
 */

import java.util.Scanner;
//import adventure_game.RoomReader;
//import org.junit.internal.runners.model.EachTestNotifier;


import adventure_game.items.HealingPotion;
import adventure_game.items.Portkey;
import adventure_game.items.RagePotion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;



public class Game {
    static Scanner in = new Scanner(System.in);
    public static Random rand = new Random();
    private Player player;
    private Room currentRoom;
    static RoomReader reader;
    Random random = new Random();

        
        
    
    public static void main(String[] args){


        //setting up game
        Game game = new Game();
       
        reader = new RoomReader();
        
        System.out.println("\n");
       

        //setting arraylist to contain all rooms,
        ArrayList<Room> rooms = reader.roomRead("data/levels/the-stilts.txt");

        //setting current room to whatever has the roomID of 0
        for (Room room: rooms){
            if (room.getRoomID() == 0){
                game.setRoom(room);
            }
        }
        game.randomAssigner(rooms);

        //checking effectiveness of random assigner;
        /*
         * 
        
        for(Room room: rooms){
            System.out.println(room.getName());
            if (room.NPCPresent()){
            System.out.println(room.getNPC().getName());
            }
            if (room.itemPresent()){
                for (Consumable item: room.getItems()){
                    System.out.println(item.getName());
                }
            }
        }
        */
        //creating player
        game.createPlayer();
        System.out.println(game.player.toString());

        // sets the players rooms to all rooms read in from the room reader
        System.out.println("Now that you have made a character, you will enter your first room on the level. Anytime you enter a room, there may be an opponent waiting to challenge you. Stay alive and find the portkey to escape!\n\n");
        game.player.setRooms(rooms);
        //game.currentRoom.addConsumable(healingPotion);
        //game.currentRoom.addConsumable(ragePotion);
        game.attackRoom();



        //will takeout later
        

     


        in.close();
    }
    /**
     * 
     * where the main function runs
     * 
     * this is where the game is played
     * 
     * 
     */
    public Game() {
        
    }

    /**
     * This function decreases the number stat points you have by the amount that you spend on an atrribute
     * 
     * @param amount amount of stat points that will be on an attribute
     * @param statsRemaining number of stat points before they are spent
     * @return reamining stat point
     */
    public int editStats(int amount, int statsRemaining){
            statsRemaining -= amount;
        return statsRemaining;
    }
/**
 * This function is to see if the user did not assign more than they had enough stat points for an attribute
 * @param attribute number of points they are trying to dedicate to the attribute
 * @param stats number of stat points left
 * @return false if they do not have enough points, true if they do
 */
    public boolean isStatValid(int attribute, int stats){
        if (attribute > stats || attribute < 0){
            return false;
        }
        return true;
    }


    /**
     * 
     * The create player function does more than create a player, it is the first stop in the game
     * This function allows you to spend stat points to customize your attributes of your character
     */
    public void createPlayer(){
        
        int mana = 0;
        int damage = 0;
        int health = 0;
        String name;
        int statPoints = 20;
        int flag = 1;

        //player = new Player("(No name Chosen)", 10, 5, 5);


        /* TO-DO */
        /* Modify this method to allow the user to create their own player */
        /* The user will specify the player's name and description, and spend */
        /* 20 points on health, mana, and baseDamage as they see fit. */
        

        System.out.println("You must first create a warrior. What would you like to name them???");
        name = in.nextLine();
        System.out.printf("Name chosen %s \n", name);
        
        System.out.println("You have 20 stat points to work with. You may divide these points as you choose among health, damage, and mana");
        System.out.println("* 1 stat point gives +10 health\n* 1 stat point gives +1 to their base damage\n* 1 stat point gives +3 mana");
        
        while(flag == 1){
        statPoints = 20;
        mana = 0;
        damage = 0;
        health = 0;

       

        
       
            System.out.printf("%d stat points left, spend them until there are none left\n\n", statPoints);

            
        
        //assinging health
        System.out.println("How many stat points would you like to devote to health?");
        
        health = in.nextInt();


        while (isStatValid(health, statPoints) == false){
            System.out.println("Error in input, try again. How many stat points would you like to devote to health?");
         
            health = in.nextInt();
        }
        statPoints = editStats(health, statPoints);
        System.out.printf("You have %d stat points remaining\n", statPoints);

        //assigning damage
        System.out.println("How many stat points would you like to devote to damage?");
 
        damage = in.nextInt();
        while (isStatValid(damage, statPoints) == false){
            System.out.println("Error in input, try again. How many stat points would you like to devote to damage?");
       
            damage = in.nextInt();
        }
        statPoints = editStats(damage, statPoints);
        System.out.printf("You have %d stat points remaining\n", statPoints);

        //assigning mana
        System.out.println("How many stat points would you like to devote to mana?");
      
        mana = in.nextInt();
        while (isStatValid(mana, statPoints) == false){
            System.out.println("Error in input, try again. How many stat points would you like to devote to mana?");
            mana = in.nextInt();
        }
     

        statPoints = editStats(mana, statPoints);
        

        
        if (statPoints <= 0){

            flag = 0;
       
            player = new Player(name, health * 10, mana * 3, damage * 5);
            player.obtain(new HealingPotion());
            player.obtain(new RagePotion());
            

        }
        else{
            System.out.println("\n\nIt seems you did not use all of your stat points. Your hero's stats are set back to 0, try again");
        }

    
    

        
    
    }
}
/**
 * Enter combat begins the taking turn process, which allows for a variety of options,
 * 
 * @param opponent the opponent you are fighting
 */
    public boolean enterCombat(NPC opponent){
        System.out.println(opponent.toString());

        System.out.printf("%s and %s are in a brawl to the bitter end.\n", this.player.getName(), opponent.getName());
        while(true){
            this.player.takeTurn(opponent);
            if(!opponent.isAlive()){
                System.out.printf("%S is SLAIN!!\n",opponent.getName());
         
                return true;
            }

            opponent.takeTurn(this.player);
            if(!this.player.isAlive()){
                System.out.printf("%S is SLAIN!!\n",this.player.getName());
                return false;
            }
        }
    }

    /**
     * Changes the current room to whatever direction chosen
     * 
     * @param direction the intended direction that the player would like to exit
     */
    public void moveRoom(int direction){
    
    if (direction == 4){
        if (this.currentRoom.hasEast()){
            this.currentRoom = this.currentRoom.getEast();
        }  
    }
    else if (direction == 3){
        if (this.currentRoom.hasWest()){
            this.currentRoom = this.currentRoom.getWest();
        }  
    }
    else if (direction == 2){
        if (this.currentRoom.hasNorth()){
            this.currentRoom = this.currentRoom.getNorth();
        }  
    }
    else if (direction == 1){
        if (this.currentRoom.hasSouth()){
            this.currentRoom = this.currentRoom.getSouth();
        }  
    }
    else{
        System.out.println("Invalid input, try again :(\n-\n-\n-\n-\n-\n");
    }
    System.out.printf("You have moved into the %s\n", this.currentRoom.getName());
    }
                

    /**
     * gets all items from a room
     * also checks if the portkey is in the room, in which case the game is won
     */
    public boolean getRoomItems(){
        if(this.currentRoom.itemPresent()){
            for (int i = 0; i < this.currentRoom.getItems().size(); i++){
                this.player.obtain(this.currentRoom.getItems().get(i));
                System.out.printf("New %s obtained!\n", this.currentRoom.getItems().get(i).getName());
                if (this.currentRoom.getItems().get(i).getName() == "Portkey"){
                    System.out.println("Congratulations!!! YOU HAVE FOUND THE PORTKEY\n-----------------\n-----------------\n-----------------\n-----------------\n-----------------");
                    System.out.println("You can use it to escape the rooms, and are now free. GAME WON!!!!!!!!!!!!");
                    return true;
                }
            }
            this.currentRoom.items.clear();
        }
        else{
        System.out.println("No items to claim in this room");
        }
        return false;
    }
    /**
     * this function is how you interact with a room. It is recursive because after interacting with a room, it asks you what other room you would like to interact with, creating an ongoing cycle
     */
    int xp = 0;


    public void attackRoom(){
        
        int level = 0;
        int flag2 = 1;
        boolean wongame = false;
        Room room = this.currentRoom;
        System.out.printf("You are in room %s: %s\n", room.getName(), room.getDescription());
        
        if (!room.NPCPresent()){
            System.out.println("No one else in this room");

            if (this.getRoomItems() == true){
                wongame = true;
                flag2 = 0;
            }

            
            
        }
        else{
            //forces player into combat with NPC. If the player wins, the function comes back true
            System.out.printf("There is a %s in the room! Defeat them to continute\n", room.getNPC().getName());
            if (this.enterCombat(room.getNPC()) == true){
                xp += 5;
                System.out.println("gained 5 xp points!");

                if (xp == 15){
                    level += 1;
                    xp = 0;
                    System.out.printf("LEVELED UP! You are now level %d\n Health and Attack increased by 5\n", level);
                    player.modifyAttack(5);
                    player.modifyHealth(5);


                }

                if (this.getRoomItems() == true){
                    wongame = true;
                    flag2 = 0;
                }
                
                room.removeNPC();
                
            }
            else{
                System.out.println("Game Over!!!");
                flag2 = 0;
            }
            }
            if (flag2 == 1){

                System.out.println("What room would you like to move into next?\n");
               

                if (!(room.getSouth() == null)){
                    System.out.print("South Room: ");
                    System.out.println(room.getSouth().getName());
                    System.out.println("                Type in 1 for south room");
                }
                
                if (!(room.getNorth() == null)){
                    System.out.print("North Room: ");
                    System.out.println(room.getNorth().getName());
                    System.out.println("                Type in 2 for north room");
                }
                if (!(room.getWest() == null)){
                    System.out.print("West Room: ");
                    System.out.println(room.getWest().getName());
                    System.out.println("                Type in 3 for west room");
                }
                if (!(room.getEast() == null)){
                    System.out.print("East Room: ");
                    System.out.println(room.getEast().getName());
                    System.out.println("                Type in 4 for east room");
                }
                
                
                if (wongame == false){
                int input = in.nextInt(); 
                moveRoom(input);

                this.attackRoom();
                }
                
            }

        
    }

    /**
     * sets the room that the game is currently in
     * @param room current room
     */
    public void setRoom(Room room){
        this.currentRoom = room;
    }
        

    /**
     * This function randomly assigns items and opponents to the rooms, once the roomreader has read all the rooms and stored them in a list
     * @param list the list of rooms that have the chance of being assigned an opponent or item
     */
    public void randomAssigner(ArrayList<Room> list){
            for (Room room: list){
                
                int badguy = random.nextInt(2);
                
                    ArrayList<String> names = new ArrayList<String>(Arrays.asList("Isaac Ratzan", "Dr Shirvani", "Professor Toups", "Demon", "Dracula", "Medusa", "Vampire", "Dark Wizard", "King Kong", "Doctor Doom"));
                    if (badguy == 0){
                        NPC opponent = new NPC(names.get(random.nextInt(10)), random.nextInt(100), random.nextInt(20), random.nextInt(70));
                        room.setOpponent(opponent);
                    }   
                int gooditem = random.nextInt(4);

                if (gooditem == 0){
                    HealingPotion healingpotion = new HealingPotion();
                    room.addConsumable(healingpotion);
                }
                if (gooditem == 1){
                    RagePotion ragepotion = new RagePotion();
                    room.addConsumable(ragepotion);
                }
                
            }
            int numRooms = list.size();
            
            int choice = random.nextInt(numRooms - 1);

            //dont put portkey in first 2 rooms
            if (choice == 0 || choice == 1){
                choice += rand.nextInt(5);
            }
            //creating portkey and placing in random rood
            Portkey portkey = new Portkey();
            list.get(choice).addConsumable(portkey);
            // assigning portkey to a random room in the game;
    }




}
