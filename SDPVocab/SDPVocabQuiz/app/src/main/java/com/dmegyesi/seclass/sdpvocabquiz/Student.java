package com.dmegyesi.seclass.sdpvocabquiz;
import java.io.Serializable;
import java.util.*;
public class Student implements Serializable{
    public String userName;
    public String major;
    public String seniorityLevel;
    public String emailAddress;
    public ArrayList<String> quizTaken=new ArrayList<String>();
    public ArrayList<String> quizCreated= new ArrayList<String>();


    public ArrayList<String> listQuizTaken(){
        return quizTaken;
    }

    //To be implemented: Register the student by creating the student class
    public boolean register(String userNameParam, String majorParam, String seniorityLevelParam, String emailAddressParam){
        userName=userNameParam;
        major=majorParam;
        seniorityLevel=seniorityLevelParam;
        emailAddress=emailAddressParam;
        LoginRegisterActivity.studentMap.put(userNameParam.toLowerCase(),this);
        return true;}
    //To be implemented: Log the student in
    public boolean login(){
        LoginRegisterActivity.loggedInUser=userName;
        return true;}

    static public boolean checkUniqueUser(String user){
        return LoginRegisterActivity.studentMap.containsKey(user);
    }
    //To be implemented: Return the quiz statistics and save the result to ApplicationBundle for UI access
    public void viewQuizStatistics(){}


}
