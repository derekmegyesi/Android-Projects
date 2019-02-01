package com.dmegyesi.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.HashMap;

public class StatsForQuizActivity extends AppCompatActivity  implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    protected void onStart(){
        super.onStart();
        try {
            setContentView(R.layout.activity_stats_for_quiz);
            TextView viewStats = (TextView) findViewById(R.id.showStats);
           // Toast popup=Toast.makeText(getApplicationContext(),LoginRegisterActivity.applicationBundle.getString("getStats"),1);
           // popup.show();
            HashMap<String,QuizResult> theTest = LoginRegisterActivity.resultMap.get(LoginRegisterActivity.applicationBundle.getString("getStats"));
            String temp="";
            for (String i :LoginRegisterActivity.quizMap.get(LoginRegisterActivity.applicationBundle.getString("getStats")).firstThree) {
                temp = temp + "\n"+i;
            }
            if (theTest.containsKey(LoginRegisterActivity.loggedInUser)) {
                if(temp.length()==0){
                    viewStats.setText("First Attempt: "+theTest.get(LoginRegisterActivity.loggedInUser).getFirstGrade().grade
                            + "\n"+"Highest Grade: " + theTest.get(LoginRegisterActivity.loggedInUser).getHighestGrade().grade
                            +"\n"+"Top Achievers: "+"\n"+"No top achievers yet.");
                }else {
                    viewStats.setText("First Attempt: " + theTest.get(LoginRegisterActivity.loggedInUser).getFirstGrade().grade
                            + "\n" + "Highest Grade: " + theTest.get(LoginRegisterActivity.loggedInUser).getHighestGrade().grade
                            + "\n" + "Top Achievers: " + temp);
                }
            }else{
                viewStats.setText("Top Achievers: "+temp);
            }

        }catch(Exception e){
            //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
            //popup.show();
        }
        Button back =(Button)findViewById(R.id.returnMainMenuButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Convert = new Intent(getApplicationContext(), QuizMainMenuActivity.class);
                startActivity(Convert);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
