package com.dmegyesi.seclass.sdpvocabquiz;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.Serializable;


public class TakeQuizActivity extends AppCompatActivity implements Serializable {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        Button next= findViewById(R.id.nextQuizButton);

        try {
            final Quiz retrievedQuiz = LoginRegisterActivity.quizMap.get(LoginRegisterActivity.applicationBundle.get("theQuiz"));
            //Toast popup=Toast.makeText(getApplicationContext(),LoginRegisterActivity.applicationBundle.get("theQuiz").toString(),1);
            //popup.show();
            final SingleQuiz firstQuiz=retrievedQuiz.generateQuiz();
            if (retrievedQuiz.index>=retrievedQuiz.NumberOfQuizes){
                next.setText("Finish");
            }
            TextView theWord = findViewById(R.id.wordLabel);
            theWord.setText(firstQuiz.theWord);
            RadioButton bt1 = findViewById(R.id.quizQuestion1);
            bt1.setText(firstQuiz.defMap.get("1"));
            RadioButton bt2 = findViewById(R.id.quizQuestion2);
            bt2.setText(firstQuiz.defMap.get("2"));
            RadioButton bt3 = findViewById(R.id.quizQuestion3);
            bt3.setText(firstQuiz.defMap.get("3"));
            RadioButton bt4 = findViewById(R.id.quizQuestion4);
            bt4.setText(firstQuiz.defMap.get("4"));
            next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retrievedQuiz.index<retrievedQuiz.NumberOfQuizes) {
                    Intent jump = new Intent(getApplicationContext(), TakeQuizActivity.class);
                    startActivity(jump);
                } else if (retrievedQuiz.index==retrievedQuiz.NumberOfQuizes){
                    try {
                        LoginRegisterActivity.applicationBundle.putString("quizPlaying", retrievedQuiz.name);
                        Intent jump2 = new Intent(getApplicationContext(), ScoreForQuizActivity.class);
                        startActivity(jump2);
                    }catch(Exception e){
                        //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
                        //popup.show();
                    }
                }
            }
        });
        RadioGroup Selected = findViewById(R.id.selectCorrectDefnRadioGroup);
        Selected.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioGroup SelectedInner = findViewById(R.id.selectCorrectDefnRadioGroup);
                int checkedRadioId=SelectedInner.getCheckedRadioButtonId();
                TextView theWordInner = findViewById(R.id.wordLabel);
                RadioButton Radio1= findViewById(R.id.quizQuestion1);
                Radio1.setEnabled(false);
                RadioButton Radio2= findViewById(R.id.quizQuestion2);
                Radio2.setEnabled(false);
                RadioButton Radio3= findViewById(R.id.quizQuestion3);
                Radio3.setEnabled(false);
                RadioButton Radio4= findViewById(R.id.quizQuestion4);
                Radio4.setEnabled(false);
                //Quiz localRetrievedQuiz=LoginRegisterActivity.quizMap.get("QZ1");
                //SingleQuiz localFirstQuiz =localRetrievedQuiz.getQuiz();
                Boolean correctness=false;
                switch(checkedRadioId){
                   case R.id.quizQuestion1:
                       correctness=firstQuiz.checkAnswer("1");
                       //theWordInner.setText(Boolean.toString(aa));
                       break;
                   case R.id.quizQuestion2:
                       correctness=firstQuiz.checkAnswer("2");
                       //theWordInner.setText(Boolean.toString(bb));
                       break;
                   case R.id.quizQuestion3:
                       correctness=firstQuiz.checkAnswer("3");
                       //theWordInner.setText(Boolean.toString(cc));
                       break;
                   case R.id.quizQuestion4:
                       correctness=firstQuiz.checkAnswer("4");
                       break;
               }
               if (correctness){
                    retrievedQuiz.NumberOfCorrect++;
                  Toast popup=Toast.makeText(getApplicationContext(),"Correct. Great Job!", Toast.LENGTH_LONG);
                  popup.show();
               }
               else{
                    Toast popup=Toast.makeText(getApplicationContext(),"Incorrect", Toast.LENGTH_LONG);
                    popup.show();
               }
            }
        });
        }
        catch(Exception e){
            TextView theWord = findViewById(R.id.wordLabel);
            theWord.setText(e.getMessage());
        }
    }

}
