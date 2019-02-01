package com.dmegyesi.seclass.sdpvocabquiz;

import java.io.Serializable;

public class SingleQuizResult implements Serializable {
    public float grade;
    public String dateAndTimeTaken;
    public SingleQuizResult(float gradeParam,String DT) {
        grade=gradeParam;
        dateAndTimeTaken=DT;
    }
}
