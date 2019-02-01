package com.dmegyesi.seclass.sdpvocabquiz;
import java.util.*;
import java.io.Serializable;

public class Quiz implements Serializable {
    //The name of the quiz
    public String name;
    //The description of the quiz
    private String description;
    public int predefNumberOfWord;
    //Store the number of words
    private int numberOfWords;
    private String addedByUser;
    //Store the word
    private ArrayList<Word> listOfWord=new ArrayList<Word>();
    //Store the incorrect defition
    private ArrayList<String> listOfincorrectDef =new ArrayList<String>();
    //This is a getter method for WORD
    public String getWord(){return "";}

    //This index is for the tracking of quiz progress
    public int index=0;
    public static SingleQuiz temp = new SingleQuiz();
    int NumberOfQuizes=0;
    int NumberOfCorrect=0;
    public ArrayList<String> firstThree = new ArrayList<String>();
    //Constructor for Quiz class
    public Quiz(String quizName, String quizDescription,int num){
        addedByUser=LoginRegisterActivity.loggedInUser;
        name=quizName;
        description=quizDescription;
        predefNumberOfWord=num;
    }
    public void buildWordOptions(String word, String correctDef, String incorrectDef_1, String incorrectDef_2, String incorrectDef_3){
        Word tempWord= new Word();
        tempWord.definition=correctDef;
        tempWord.word=word;
        listOfWord.add(tempWord);
        listOfincorrectDef.add(incorrectDef_1);
        listOfincorrectDef.add(incorrectDef_2);
        listOfincorrectDef.add(incorrectDef_3);
        NumberOfQuizes++;
    }

    static public boolean checkQuizNameUnique(String quizName){
        boolean uniqe = LoginRegisterActivity.quizMap.containsKey(quizName);
        return uniqe;
    }
    public void finishBuildingQuiz(){
        LoginRegisterActivity.quizMap.put(name,this);
        LoginRegisterActivity.studentMap.get(LoginRegisterActivity.loggedInUser).quizCreated.add(name);




    }
    //Following methods needs to be implemented
    public SingleQuiz generateQuiz(){
        Collections.shuffle(listOfWord);
        String theWord = listOfWord.get(index).word;
        String rightDef= listOfWord.get(index).definition;
        SingleQuiz temp = new SingleQuiz();
        Collections.shuffle(listOfincorrectDef);
        temp.theWord=theWord;
        ArrayList<String> randVec = new ArrayList<String>();
        randVec.add("1");
        randVec.add("2");
        randVec.add("3");
        randVec.add("4");
        Collections.shuffle(randVec);
        temp.defMap.put(randVec.get(0),rightDef);
        temp.defMap.put(randVec.get(1),listOfincorrectDef.get(0));
        temp.defMap.put(randVec.get(2),listOfincorrectDef.get(1));
        temp.defMap.put(randVec.get(3),listOfincorrectDef.get(2));
        temp.correctAnswer=randVec.get(0);
        index++;
        return temp;
    }

    public boolean takeQuiz(String Sel){
        boolean check = temp.checkAnswer(Sel);
        if (check) {
            NumberOfCorrect++;
        }
        return check;
    }

    public SingleQuiz getQuiz(){
        return temp;
    }
    public float showResults(){
        float percent = 100*(float)NumberOfCorrect/(float)index;
        NumberOfCorrect=0;
        index=0;
        if (percent==100&&firstThree.size()<3&&!firstThree.contains(LoginRegisterActivity.loggedInUser)){
            firstThree.add(LoginRegisterActivity.loggedInUser);
            Collections.sort(firstThree);
            if(!LoginRegisterActivity.studentMap.get(LoginRegisterActivity.loggedInUser).quizTaken.contains(name)){
            LoginRegisterActivity.studentMap.get(LoginRegisterActivity.loggedInUser).quizTaken.add(name);}
            Collections.sort(firstThree);
        }
        String DT = new Date().toString();
        if (LoginRegisterActivity.resultMap.containsKey(name)){
            if(LoginRegisterActivity.resultMap.get(name).containsKey(LoginRegisterActivity.loggedInUser)){
                LoginRegisterActivity.resultMap.get(name).get(LoginRegisterActivity.loggedInUser).addQuizResult(percent,LoginRegisterActivity.loggedInUser,name,DT);
            }
            else{
                QuizResult tempResult = new QuizResult();
                tempResult.addQuizResult(percent,LoginRegisterActivity.loggedInUser,name,DT);
                LoginRegisterActivity.resultMap.get(name).put(LoginRegisterActivity.loggedInUser,tempResult);
            }
        }else{
            QuizResult tempResult = new QuizResult();
            tempResult.addQuizResult(percent,LoginRegisterActivity.loggedInUser,name,DT);
            HashMap<String,QuizResult> tempM1 = new HashMap<>();
            tempM1.put(LoginRegisterActivity.loggedInUser,tempResult);
            LoginRegisterActivity.resultMap.put(name,tempM1);
        }
        if(!LoginRegisterActivity.studentMap.get(LoginRegisterActivity.loggedInUser).quizTaken.contains(name)){
            LoginRegisterActivity.studentMap.get(LoginRegisterActivity.loggedInUser).quizTaken.add(name);}
        return percent;
    }
    public static void removeQuiz(String quizName){
        LoginRegisterActivity.quizMap.remove(quizName);
        LoginRegisterActivity.resultMap.remove(quizName);
        LoginRegisterActivity.studentMap.get(LoginRegisterActivity.loggedInUser).quizCreated.remove(quizName);
        for (Map.Entry<String,Student> i :LoginRegisterActivity.studentMap.entrySet()){
            i.getValue().quizTaken.remove(quizName);
        }
    }

}
