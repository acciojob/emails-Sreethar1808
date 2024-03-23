package com.driver;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    ArrayList<inbox> recieved;
    ArrayList<inbox> trash;

    int currentcapacity;
    public Gmail(String emailId, int inboxCapacity) {
           super(emailId);
           this.inboxCapacity=inboxCapacity;
           recieved = new ArrayList<>();
           trash = new ArrayList<>();

        currentcapacity=0;
    }




    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(currentcapacity==inboxCapacity)
        {
            inbox inb = recieved.remove(0);
            trash.add(inb);
            currentcapacity--;
        }
        currentcapacity++;
        recieved.add(new inbox(date, sender, message));
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0;i<recieved.size();i++)
        {
            String sms = recieved.get(i).message;
            if(sms.equals(message))
            {
                inbox inb = recieved.remove(i);
                trash.add(inb);
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(recieved.size()==0)
        {
            return null;
        }
        else {
            return recieved.get(recieved.size()-1).message;
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(recieved.size()==0)
        {
            return null;

        }
        else {
            return recieved.get(0).message;
        }

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        if (recieved == null || recieved.isEmpty()) {
            return 0;
        }

        int count = 0;
        for (inbox receivedD : recieved) {
            // Check if the received date is within the specified range
            Date receivedDate = receivedD.date;
            if (receivedDate.compareTo(start) >= 0 && receivedDate.compareTo(end) <= 0) {
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
       return recieved.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
       trash=new ArrayList<>();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox

        return inboxCapacity;
    }
}
