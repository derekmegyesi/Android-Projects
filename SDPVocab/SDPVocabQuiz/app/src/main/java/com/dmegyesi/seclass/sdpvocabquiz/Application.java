package com.dmegyesi.seclass.sdpvocabquiz;

import java.io.Serializable;

public class Application  implements Serializable {
    public String version;
    public String systemInfo;
    public String instanceID;
    //To be implemented
    public boolean login(String userName){return true;}
    public boolean register(String userName, String major, String seniorityLevel, String emailAddress){return true;}
    public void takeQuiz(){}
    public boolean addQuiz(String word, String correctDef, String incorrectDef_1, String incorrectDef_2, String incorrectDef_3){return true;}
    public boolean removeQuiz(){return true;}
    public void viewStatistics(){}
    private void sortQuiz(){}
}
