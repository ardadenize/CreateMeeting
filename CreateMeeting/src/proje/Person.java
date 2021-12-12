package proje;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Person object
 * 
 * @author arda
 */
public class Person {
private String name ;
private ArrayList<Meeting> myMeetings;
private ArrayList<Meeting> iOrganize;
private int id;

/**
 * Cosntructor
 * @param name person Name of the person
 */
 Person(String name){
 this.name=name;
 this.myMeetings = new ArrayList<>();
 this.iOrganize = new ArrayList<>();
 Random r = new Random();
 this.id = r.nextInt()*1;
}
/*
* Equals method to check whether two Person instances are the same
*/ 
public Boolean equals(Person p){
 /**
 * If p is equal this  person, returns true
 */
    if(p == this){
        return true;
    }else{
        return false;
    }
}
/**
 * Create a meeting.
 * @param m Meeting which will be added to person
 */
public Boolean addMeeting(Meeting m){
   for (int i = 0; i < myMeetings.size(); i++) {
       if(myMeetings.get(i).getdate() == m.getdate()){
           return false;
       }      
   }
   myMeetings.add(m);
   return m.addAttendee(this);
}
/**
 * Delete a meeting.
 */
public Boolean removeMeeting(Meeting m){
    int index = myMeetings.indexOf(m);
    Boolean status = myMeetings.get(index).removeAttendee(this);
    
    return status;
}

public void organizeMeeting(Meeting m){
    iOrganize.add(m);
}
/**
 * This method will cancel 
 * @param m Meeting which will be canceled. 
 */
public void cancelMeeting(Meeting m){
    
    int index = iOrganize.indexOf(m);
    /**
     * Check if is host owner this organization
     */
    if(iOrganize.get(index).gethost() == this ){
        /**
         * Clear all attentes.
         */                         
        iOrganize.get(index).removeAllAttendees();
        iOrganize.remove(index);
    }
}
/**
 * Display meetings.
 */
public void displayMyMeetings(){
    if(myMeetings.size()>0){
    for (int i = 0; i < myMeetings.size() ; i++) {
         /**
         * Show all meetings.
         */    
       System.out.println(i + ". Name : " +myMeetings.get(i).getname() + " Date : " + myMeetings.get(i).getdate());  
    }  
    }else{
        System.out.println("Your agenda is empty.");
    }
}
 /**
 * Display organizations.
 */    
public void displayMyOrganizations(){
    if(iOrganize.size()>0){
    for (int i = 0; i < iOrganize.size(); i++) {
       System.out.println(i + ". Name : " + iOrganize.get(i).getname()+ " Date: " +iOrganize.get(i).getdate());  
   }     
    }else {
        System.out.println("You didn't organized anything yet.");
    }
}
public void setName(String name){
    this.name = name;
}
public String getName(){
    return this.name;
}

public int getID(){
    return this.id;
}
public void setmyMeetings(ArrayList<Meeting> myMeetings){
    this.myMeetings = myMeetings;
}
public ArrayList<Meeting> getmyMeetings(){
    return this.myMeetings;
}
public void setiOrganize(ArrayList<Meeting> iOrganize){
    this.iOrganize = iOrganize;
}
public ArrayList<Meeting> getiOrganize(){
    return this.iOrganize;
}

public void showInfos(){
    System.out.println("Name : " + this.name);
    System.out.println("Meetings : " + this.myMeetings );
    System.out.println("Organize : " + this.iOrganize );
        
}        
}
