package com.dmegyesi.seclass.sdpvocabquiz;

import java.io.Serializable;
import java.util.*;

public class SingleQuiz implements Serializable{
    public String theWord;
    public HashMap<String,String> defMap =new HashMap<String,String>();
    public String correctAnswer;
    public boolean checkAnswer(String Ans){
        boolean temp=correctAnswer.equals(Ans);
        return temp;
    }
}
