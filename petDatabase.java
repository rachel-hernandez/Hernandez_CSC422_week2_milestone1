/*
 * Rachel Hernandez 
 * CSC 422 Software Engineering 
 * week 2 assignment 2, part 2
 * milestone 1: reading from and saving to a file text
 */

//importing needed utilitis 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Rachel
 */
public class petDatabase {
    
    //creating 2D arrayList
    static ArrayList<ArrayList<String>> pets = new ArrayList<ArrayList<String>>();
    
    //int to store the users choice
    static int choice;
    
    //int to count the number of rows in the 2D arrayList
    static int row;
    
    //declaring a scanner
    static Scanner s = new Scanner (System.in);


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Pet Database Program.");
         //Try ... Catch to open and read a file line by line
        try {
            File myObj = new File("petDatabase.txt");
            try (Scanner myReader = new Scanner(myObj)) {
                int row = 0;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    //splitting the line of the file at the "|" character 
                    String[] fileLine = data.trim().split("\\|");
                    
                    //if the value is not null it is added to the 2D arrayList
                    if (fileLine != null) {
                        int col = 0;
                        for (String str : fileLine) {
                            
                            if (str != null) {
                                pets.add(new ArrayList<>());
                                pets.get(row).add(col, str);
                                col++;
                            }

                        }
                        row++;
                    }

                   
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        
       //If statements to remove null elements from the arrayList
        int petCount = pets.size();
        for (int i = 0; i < petCount; i++) {
            int infoCount = pets.get(i).size();
            
            if (infoCount == 0) {
                pets.remove(i);
                petCount = pets.size();
                i = 0;
            } else {
                
            }
        }
        
        System.out.println(pets);
        
        //options menu
        //loop will break when user enters 4
        while(true && choice !=4){
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Remove an existing pet");
            System.out.println("4) Exit program");
            System.out.println("Your choice: ");
            choice = s.nextInt(); 
            System.out.println(" ");
                
            //Switch statment to determine action based on users choice 
            switch(choice){
                //if user chooses 1 all pets will be displayed 
                case 1 -> {
                    //calling method to display all the pets 
                    viewPets();
                }

                //if user chooses 2 the user can add more pets
                case 2 -> {
                    //calling method to add more pets 
                    addPets();
                }
                
                //if user chooses 3 to remove a pet
                case 3 -> {
                    //calling method to remove a pet
                    removePet();
                }
                
           
                //if user chooses 4 to end the program 
                case 4 -> exitProgram();
                    
                default -> exitProgram();
            }
        }
        
    }
    
    //method to display all the pets
    static boolean viewPets(){
        System.out.println(" ");
        System.out.printf("\n+-------------------+");
        System.out.printf("\n| %-3s| %-8s| %-3s|%n", "ID", "Name", "Age");
        System.out.printf("+-------------------+");
        int count = 0;
        int petCount = pets.size();
        //for loop to loop throudh all the rows of the 2D arrayList
        for (int i = 0; i < petCount; i++) {            
            String petID = pets.get(i).get(0);
            String name = pets.get(i).get(1);
            String age = pets.get(i).get(2);
            //displaying the values in the arrayList
            System.out.printf("\n| %-3s| %-8s| %-3s|%n", petID, name, age);
            count++;
        }
        System.out.println("+-------------------+");
        System.out.println(count + " rows in set.");
        return true;
    }
    
    //method to add more pets
    static boolean addPets(){
        //value to keep the while loop going
        int keepLooping = 0;  
        int petCount = pets.size();
        //while loop to add pet info 
        while(keepLooping !=1){            
            System.out.println("add pet (name, age) or done to stop: ");
            String petName = s.next();            
            
            //loop will end when user enters "done"
            if(petName.equalsIgnoreCase("done")){
                keepLooping = 1;
                //break;
            }else{
                //adding pet into to the 2D arrayList
                String petAge = s.next();
                String newPetID = Integer.toString(petCount);
                pets.add(petCount,new ArrayList<>(Arrays.asList(newPetID, petName, petAge)));
                petCount++;
            }           
            
        }
        
        return true;
        
    }
    
    
    
   
    //method to remove an existing pet
    static boolean removePet(){
        System.out.println(" ");
        System.out.printf("\n+-------------------+");
        System.out.printf("\n| %-3s| %-8s| %-3s|%n", "ID", "Name", "Age");
        System.out.printf("+-------------------+");
        int count = 0;
        int petCount = pets.size();
        //for loop to loop throudh all the rows of the 2D arrayList
        for (int i = 0; i < petCount; i++) {            
            String petID = pets.get(i).get(0);
            String name = pets.get(i).get(1);
            String age = pets.get(i).get(2);
            //displaying the values in the arrayList
            System.out.printf("\n| %-3s| %-8s| %-3s|%n", petID, name, age);
            count++;
        }
        System.out.println("+-------------------+");
        System.out.println(count + " rows in set.");
        System.out.println(" ");
        //Getting the ID of the pet to be removed
        System.out.println("Enter the pet ID to remove: ");
        int petID = s.nextInt();
        //Getting that pets name and age
        String name = pets.get(petID).get(0);
        String age = pets.get(petID).get(1);
        //displaying removed pets name and age
        System.out.println(name + " " + age + " is removed");
        //removing pet form the 2d arrayList
        pets.remove(petID);
        //the arrayList automatically updates row ID numbers
        return true;
    }
   
    //method to end the program and save pets ArrayList to file
    static boolean exitProgram() {
        try {
            try (FileWriter updateFile = new FileWriter("petDatabase.txt")) {
               
                int petCount = pets.size();
                //for loop to loop throudh all the rows of the 2D arrayList
                for (int i = 0; i < petCount; i++) {
                    String petID = pets.get(i).get(0);
                    String name = pets.get(i).get(1);
                    String age = pets.get(i).get(2);
                    //writting data to the file
                    updateFile.write(petID +"|"+name+"|"+age +"\n");
                }
                updateFile.close();
            }
            System.out.println("It worked");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            //e.printStackTrace();
        }
        System.out.println("Goodbye!");
        return false;
    }


}

/*
0|Kitty|7
1|Mikey|15
2|Blue|4
3|Fido|3
4|Hunter|18
*/