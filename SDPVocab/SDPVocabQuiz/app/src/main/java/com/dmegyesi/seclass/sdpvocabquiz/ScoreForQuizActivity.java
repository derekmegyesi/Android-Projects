package com.dmegyesi.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ScoreForQuizActivity extends AppCompatActivity  implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_for_quiz);

    }
    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_score_for_quiz);
        try{
            TextView A= findViewById(R.id.scoreForQuizBanner);
            //Toast popup=Toast.makeText(getApplicationContext(),LoginRegisterActivity.applicationBundle.getString("quizPlaying"),1);
            //popup.show();
            Quiz playedQuiz = LoginRegisterActivity.quizMap.get(LoginRegisterActivity.applicationBundle.getString("quizPlaying"));
            float score = playedQuiz.showResults();
            String playerName =LoginRegisterActivity.loggedInUser;
            final EditText UserName= findViewById(R.id.studentUsername);
            UserName.setText(playerName.substring(0,1).toUpperCase() + playerName.substring(1).toLowerCase());

            final EditText Score = findViewById(R.id.quizScore);
            Score.setText(String.valueOf(score).toString());

            //Toast popup= Toast.makeText(getApplicationContext(),"",1);
            //popup.show();
        }catch(Exception e){
            //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
            //popup.show();
        }
        Button returnMain = findViewById(R.id.returnMainMenuButton);
        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Convert = new Intent(getApplicationContext(), QuizMainMenuActivity.class);
                startActivity(Convert);
            }
        });
    }
    protected void onPause(){
        super.onPause();


        try{
            try {
                FileOutputStream os_result = openFileOutput("result", MODE_PRIVATE);
                ObjectOutputStream oo_result = new ObjectOutputStream(os_result);
                oo_result.writeObject(LoginRegisterActivity.resultMap);
                //Toast popup=Toast.makeText(getApplicationContext(),"Save Result",1);
                //popup.show();
                oo_result.close();
                os_result.close();
            }catch(Exception f){
                //Toast popup=Toast.makeText(getApplicationContext(),f.getMessage(),1);
                //popup.show();
            }
        } catch (Exception e) {
            //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
            //popup.show();
        }
        try{
            FileOutputStream os_Student = openFileOutput("student",MODE_PRIVATE);
            ObjectOutputStream oo_Student = new ObjectOutputStream(os_Student);
            oo_Student.writeObject(LoginRegisterActivity.studentMap);
            os_Student.close();
            oo_Student.close();
            //Toast popup=Toast.makeText(getApplicationContext(),"Save Student",1);
            //popup.show();
        } catch (Exception e) {
            //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
            //popup.show();
        }
        try{
            try {
                FileOutputStream os_quiz = openFileOutput("quiz", MODE_PRIVATE);
                ObjectOutputStream oo_quiz = new ObjectOutputStream(os_quiz);
                oo_quiz.writeObject(LoginRegisterActivity.quizMap);

                //Toast popup=Toast.makeText(getApplicationContext(),"Quiz written",1);
                //popup.show();
                oo_quiz.close();
                os_quiz.close();

            }catch(Exception f){
                //Toast popup=Toast.makeText(getApplicationContext(),f.getMessage(),1);
                //popup.show();
            }
        } catch (Exception e) {
            //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
            //popup.show();
        }

    }

}
