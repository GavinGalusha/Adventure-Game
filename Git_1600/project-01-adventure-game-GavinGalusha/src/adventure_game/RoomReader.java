package adventure_game;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomReader {
    private ArrayList<Room> rooms;
    
    /**
     * This function takes in a file, and parses it to generate rooms, which it then adds to a list and returns
     * @param fileName the file to be read from
     * @return returns a list of rooms
     */
    public ArrayList<Room> roomRead(String fileName){
        // this function is reading a file, and creating rooms based on the information in that file


        rooms = new ArrayList<>();
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)){
            
            
            while (scanner.hasNextLine()){

                String string = scanner.nextLine().strip();
                //string is equal to the line of text we are analyzing



                if (string.isEmpty() || string.startsWith("#")){
                  
                    //ignore these lines, and jump back up to the top to analyze next line
                    continue;
                }

                 if (rooms.isEmpty()) {
                    //if rooms is empty this means that it is the first part of the file.
                    int numRooms = Integer.parseInt(string);
                    //capturing totla number of rooms, so we can run a for loop to take in all the roomID's, names and descriptions
        
                    for (int i = 0; i < numRooms; i++) {
                        //takes the first number as the total number of rooms, then runs a for loop to collect the id and description of all the rooms
                        String[] list = scanner.nextLine().split(":");
                        int roomID = Integer.parseInt(list[0]);
                        String roomName = list[1];
                        
                        String roomDescription = list[2];
                        

                        Room newRoom = new Room(roomID, roomName, roomDescription);
                        
                        // assign NPC and items here 

                        rooms.add(newRoom);
                        }
                    }
                    
                    else{
                    
                    String[] list = string.split(":");

                    int roomID = Integer.parseInt(list[0]);
                    rooms.get(roomID);
            


                    int eastID = Integer.parseInt(list[1].strip());
                    int northID = Integer.parseInt(list[2].strip());
                    int westID = Integer.parseInt(list[3].strip());
                    int southID = Integer.parseInt(list[4].strip());

                    if (!(eastID == -1)){
                    rooms.get(roomID).setEast(rooms.get(eastID));
                    }
                    if (!(northID == -1)){
                    rooms.get(roomID).setNorth(rooms.get(northID));
                    }
                    if (!(westID == -1)){
                    rooms.get(roomID).setWest(rooms.get(westID));
                    }
                    if (!(southID == -1)){
                    rooms.get(roomID).setSouth(rooms.get(southID));
                    }
                    }
                    
                    
                    
                    }
                
                }    

                catch(FileNotFoundException e){

                    System.out.println("File was not found, failed to open any rooms");

                }

                catch(Exception e){

                    System.out.println("random exception");
                }
        
            

                if (!rooms.isEmpty()){
                System.out.println("File read Successfully. Rooms have been implemented");
                }

                return rooms;
            }



            }

        

    


