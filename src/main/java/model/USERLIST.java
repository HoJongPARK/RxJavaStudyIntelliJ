package model;

import java.util.ArrayList;

public class USERLIST {
    private static USERLIST instance;
    private ArrayList<USER> userList;

    public static USERLIST getInstance() {
        if(instance==null)
            instance= new USERLIST();
        return instance;
    }

    private USERLIST(){
        userList = new ArrayList<>();
    }

     public void addNewUser(USER user){
        userList.add(user);
     }
     public USER getUser(int index){
        return userList.get(index);
     }
     public void removeUser(int index){
        userList.remove(index);
     }
     public ArrayList<USER> getUserList(){
        return this.userList;
     }
}
