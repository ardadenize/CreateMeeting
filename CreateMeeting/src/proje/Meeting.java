/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author arda
 */
public class Meeting {
private String name;
private String date ;
private ArrayList<Person> attendees;
private Person host;

Meeting(String date,String name, Person p){
 this.date=date;
 this.name = name;
 this.host = p;
 attendees = new ArrayList<>();
}

public Boolean equals(Meeting m){
   if((m.getdate()==this.date) && (m.gethost() == this.host) && (m.getattendees() == this.attendees)){
       return true;
   }else{
       return false;
   }
}

public Boolean addAttendee(Person p){
    
  if(attendees.contains(p)){
      return false;
  }else{
      
  attendees.add(p);
           return true;
  }
}

public Boolean removeAttendee(Person p){
   if(attendees.contains(p)){
      attendees.remove(attendees.indexOf(p));
      return true;
  }else{
   return false;
   }
   
}

public void removeAllAttendees(){
    
for(int i =0; i<attendees.size();i++){
 attendees.get(i).removeMeeting(this);
}

}

public String toString(){
    String allOf;
    allOf = this.date.toString();
    allOf = allOf + "\n" + "ATTENDEES" + "\n" + attendees.toString()+"\n" + "Host" +"\n" + this.host.getName();
 
    return allOf;
}
        
public void setdate(String date){
    this.date = date;
}
public String getdate(){
    return this.date;
}
public void setattendees(ArrayList<Person> attendees){
    this.attendees = attendees;
}
public List<Person> getattendees(){
    return this.attendees;
}
public void sethost(Person host){
    this.host = host;
}
public Person gethost(){
    return this.host;
}

public String getname(){
    return this.name;
}
public void setname(String n){
   this.name = n;
}

public void showInfos(){
    System.out.println("Date : " + this.date);
        System.out.println("Attendees : " + this.attendees );
        System.out.println("Hosts : " + this.host );
        
}        


    
}
