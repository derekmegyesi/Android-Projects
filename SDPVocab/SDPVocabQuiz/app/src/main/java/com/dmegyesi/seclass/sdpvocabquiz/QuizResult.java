package com.dmegyesi.seclass.sdpvocabquiz;

import java.io.Serializable;
import java.util.*;
public class QuizResult implements Serializable {
    public String testTakerUserName;
    public String testName;
    public String latestDate;
    public ArrayList<SingleQuizResult> gradeList= new ArrayList<SingleQuizResult>();
    //To be implemented
    public void addQuizResult(float grade, String testTaker, String testNameParam, String DT) {
        testTakerUserName = testTaker;
        testName = testNameParam;
        SingleQuizResult temp = new SingleQuizResult(grade, DT);
        latestDate = DT;
        gradeList.add(temp);
        Collections.sort(gradeList, new Comparator<SingleQuizResult>() {
            @Override
            public int compare(SingleQuizResult o1, SingleQuizResult o2) {
                return o1.dateAndTimeTaken.compareTo(o2.dateAndTimeTaken);
            }

        });
        //Collections.reverse(gradeList);
    }
    public SingleQuizResult getFirstGrade(){return gradeList.get(0);}
    public SingleQuizResult getHighestGrade(){
        SingleQuizResult temp =gradeList.get(0);
        for(int i=0;i<gradeList.size();i++){
            if(gradeList.get(i).grade>temp.grade){
                temp=gradeList.get(i);
            }
        }
        return temp;
    }
}
