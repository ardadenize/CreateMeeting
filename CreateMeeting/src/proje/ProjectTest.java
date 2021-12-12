/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author arda
 */
public class ProjectTest {
    
    public static void main(String[] args){
        
    ArrayList<Meeting> allMeetings = new ArrayList<>();
    ArrayList<Meeting> cancelMeeting = new ArrayList<>();
    ArrayList<Person> users = CreateUsers(allMeetings);
     menu(allMeetings, users,null);
        
    }
    public static void menu(ArrayList<Meeting> allMeetings, ArrayList<Person> users, Person loggedUser) {
   
        String selection;
        Scanner input = new Scanner(System.in);
        
        
        System.out.println("-------------------------\n");
        System.out.println("Please, login before following transactions");
        System.out.println("0 - Login");
        System.out.println("1 - Create and host a new meeting ");
        System.out.println("2 - Cancel a meeting ");
        System.out.println("3 - Attend an existing meeting");
        System.out.println("4 - Leave a meeting");
        System.out.println("5 - Display my Meetings ");
        System.out.println("6 - Display Meetings organized by me");
        System.out.println("7 - Logout");
        System.out.println("8 - Exit: quits the app.");
        selection = input.next();
        
      switch (selection) {
          case "0":
            loggedUser = login(allMeetings,users);
            menu(allMeetings,users,loggedUser);
            break;
        case "1":
            if(createMeeting(allMeetings,loggedUser)){
                System.out.println("Succesfully added.");
                menu(allMeetings,users,loggedUser);
            }else{
                System.out.println("Error.");
                menu(allMeetings,users,loggedUser);
            };
            break;
        case "2":
        {
            cancelmeeting(allMeetings,loggedUser);
             menu(allMeetings,users,loggedUser);
        }
            break;
        case "3":
          attendMeeting(allMeetings,loggedUser);
          menu(allMeetings,users,loggedUser);
            break;
        case "4":
          leaveMeeting(allMeetings,loggedUser);
         
            break;
        case "5":
          loggedUser.displayMyMeetings();
          menu(allMeetings,users,loggedUser);
            break;
        case "6":
         loggedUser.displayMyOrganizations();
         menu(allMeetings,users,loggedUser);
            break;
        case "7":
        if(logout(loggedUser)){
                System.out.println("Succesful.");
                menu(allMeetings,users,loggedUser);
            }else{
                System.out.println("Error.");
                menu(allMeetings,users,loggedUser);
            };
          
          
            break;
        case "8":
         System.exit(0);
            break;
        default:
         System.out.println("Incorrect process, please try again.");
          menu(allMeetings,users,loggedUser);
            break;
    }
    }
    public static Boolean createMeeting(ArrayList<Meeting> allMeetings, Person loggedUser){
        Scanner scan = new Scanner(System.in);
         System.out.println("Please, login before create a meeting.");
         System.out.println("Enter Name of Meeting : ");
         String name = scan.nextLine();
         System.out.println("Enter Date of Meeting (dd.mm.yyyy) : ");
         String date = scan.nextLine();
         Meeting m = new Meeting(date,name,loggedUser);
         allMeetings.add(m);
         loggedUser.organizeMeeting(m);
         return true;
    }
    
    public static void  cancelmeeting(ArrayList<Meeting> allMeetings,Person loggedUser){
        
        
        if(loggedUser.getiOrganize().size()>0){
         loggedUser.displayMyOrganizations();
        System.out.println("Which one would you like to delete ?");
         Scanner scan = new Scanner(System.in);
         int index = scan.nextInt();
          Meeting cancelMe = allMeetings.get(index);
            System.out.println("The organization you selected has been deleted.");
          
         loggedUser.cancelMeeting(cancelMe);
        }else{
            System.out.println("You didn't organize anything.");
        }
    }
    
    public static void leaveMeeting(ArrayList<Meeting> allMeetings,Person loggedUser){
        if(loggedUser.getmyMeetings().size()>0){
        loggedUser.displayMyMeetings();
        System.out.println("Which one would you like to leave ?");
         Scanner scan = new Scanner(System.in);
         int index = scan.nextInt();
         Meeting cancelMe = allMeetings.get(index);

         if(loggedUser.removeMeeting(cancelMe) == true){
             System.out.println("Succesfull");
         }else if(loggedUser.removeMeeting(cancelMe) == false){
             System.out.println("Error");
    }
        }else{
             System.out.println("Your agenda is empty.");
        }
    }
    

    private static void attendMeeting(ArrayList<Meeting> allMeetings, Person user){
        if(allMeetings.size()>0){
        for (int i = 0; i < allMeetings.size() ; i++) {
         /**
         * Show all meetings.
         */    
       System.out.println(i + ". Name : " +allMeetings.get(i).getname() + " Date : " + allMeetings.get(i).getdate());  
    } 
        System.out.println("Which one would you like to join ?(Please enter number of meeeting)");
         Scanner scan = new Scanner(System.in);
         int indexofMeeting = scan.nextInt();
         Meeting newMeeting = allMeetings.get(indexofMeeting);
        user.addMeeting(newMeeting); 
            System.out.println("Succesfully joined.");
        }else{
            System.out.println("There is no Meeting.");
        }
    }
    
    public static Boolean logout( Person loggedUser){
        try{
        loggedUser = null;
        return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public static Person login(ArrayList<Meeting> allMeetings, ArrayList<Person> users){
        Scanner scan = new Scanner(System.in);
        String input;
        System.out.println("Enter user name to login:");
        input = scan.nextLine();
        ArrayList<String> UsersName = new ArrayList<>();
        Person loggedUser = null;
        for(int i = 0; i<users.size();i++){
            UsersName.add(users.get(i).getName());
        }
        if(UsersName.contains(input)){
               loggedUser = users.get(UsersName.indexOf(input));
           
        }else{
          System.out.println("User not found.");
        }
    return loggedUser;    
    }
    
   public static ArrayList<Person> CreateUsers(ArrayList<Meeting> allMeetings){
       ArrayList<Person> users = new ArrayList<>();
       Scanner scan = new Scanner(System.in);
       String input;
       Person user = null;
      System.out.println("Create a list of users, -1 to continue with menu ");
      do{
           System.out.println("Enter username: ");
           input = scan.nextLine();
           if(!"-1".equals(input)){ 
           user = new Person(input);
           users.add(user);
           System.out.println(user.getName()+"   "+user.getID());
           }
      }while (!"-1".equals(input));  
    menu(allMeetings,users,null);
    return users;
   }
}
