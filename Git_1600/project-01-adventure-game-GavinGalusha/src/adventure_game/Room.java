package adventure_game;

import java.util.ArrayList;
import adventure_game.items.Consumable;


public class Room {
    
    int roomID;
    String name = "null";
    String description;
    Room east;
    Room north;
    Room west;
    Room south;
    Boolean hasNPC = false;
    NPC opponent;

    ArrayList<Consumable> items;


    /**
     * constructor
     * @param roomID sets room ID 
     * @param roomName sets roomName
     * @param description sets room Description
     */
    public Room(int roomID, String roomName, String description){

        this.roomID = roomID;
        this.name = roomName;
        this.description = description;
        
        this.north = null;
        this.east = null;
        this.west = null;
        this.south = null;
        this.opponent = null;
        this.items = new ArrayList<Consumable>();
        

    }
    /**
     * returns east room
     * @return east room
     */
    public Room getEast() {
        return this.east;
    }
    /**
     * sets east room
     * @param east east room to be set
     */
    public void setEast(Room east) {
        this.east = east;
    }
    
    /**
     * returns north room
     * @return north room
     */
    public Room getNorth() {
        return this.north;
    }
    /**
     * sets north room
     * @param north east room to be set
     */
    public void setNorth(Room north) {
        this.north = north;
    }
    /**
     * returns west room
     * @return west room
     */
    public Room getWest() {
        return this.west;
    }
    /**
     * sets west room
     * @param west east room to be set
     */
    public void setWest(Room west) {
        this.west = west;
    }
    /**
     * gets south room
     * @return south room
     */
    public Room getSouth() {
        return this.south;
    }
    /**
     * sets south room
     * @param south south room to be set
     */
    public void setSouth(Room south) {
        this.south = south;
    }
    /**
     * returns opponent in room
     * @return opponent 
     */
    public NPC getOpponent() {
        return this.opponent;
    }
    /**
     * set an opponent for this room
     * @param opponent opponent being set
     */
    public void setOpponent(NPC opponent) {
        this.opponent = opponent;
        this.hasNPC = true;
    }
    /**
     * returns list of items in room
     * @return list of items
     */
    public ArrayList<Consumable> getItems() {
        return this.items;
    }
    /**
     * adds item to room
     * @param item item to be added
     */
    public void addItem(Consumable item) {
        this.items.add(item);
    }
    /**
     * removes item
     * @param item item to be removed
     */
    public void removeItem(Consumable item) {
        this.items.remove(item);
    }

    /**
     * checks if npc is presnet
     * @return true or false
     */
    public boolean NPCPresent(){
        if (this.hasNPC == true){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * returns NPC
     * @return NPC
     */
    public NPC getNPC(){
        return this.opponent;
    }

    /**
     * checks if an item is present
     * @return true or false
     */
    public boolean itemPresent(){
        if (this.items.isEmpty()){
        return false;
        }
        else{
            return true;
        }
    }

    
    /**
     * adds a consumable to the room
     * @param consumable the added consumable
     */
    public void addConsumable(Consumable consumable){
        this.items.add(consumable);
    }



/**
 * returns the name of room
 * @return the name of the room
 */
    public String getName(){
        return this.name;
    }

    /**
     * returns the description of the room
     * @return description of room
     */
    public String getDescription(){
        return this.description;
    }
    /**
     * returns room ID
     * @return room ID
     */
    public int getRoomID(){
        return this.roomID;
    }
    /**
     * checks if room has south exit
     * @return true or false
     */
    public boolean hasSouth(){
        return !(this.getSouth() == null);
    }
    /**
     * checks if room has north exit
     * @return true or false
     */
    public boolean hasNorth(){
        return !(this.getNorth() == null);
    }
    /**
     * checks if room has east exit
     * @return true or false
     */
    public boolean hasEast(){
        return !(this.getEast() == null);
    }
    /**
     * checks if room has west exit
     * @return true or false
     */
    public boolean hasWest(){
        return !(this.getWest() == null);
    }
    /**
     * removes npc from room and sets this.hasNPC to false
     */
    public void removeNPC(){
        this.opponent = null;
        this.hasNPC = false;
    }

    
    

}
