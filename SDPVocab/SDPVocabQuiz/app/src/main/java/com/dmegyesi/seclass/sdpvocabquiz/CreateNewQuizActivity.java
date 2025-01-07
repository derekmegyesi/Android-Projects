package com.dmegyesi.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.Serializable;

public class CreateNewQuizActivity extends AppCompatActivity  implements Serializable {

    @Override
    protected void onStart(){
        super.onStart();
        LoginRegisterActivity.applicationBundle.putInt("wordAdded",0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_quiz);
        final EditText quizNameInput= findViewById(R.id.quizName);
        quizNameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(Quiz.checkQuizNameUnique(quizNameInput.getText().toString())&&quizNameInput.getText().length()!=0){
                        Toast popup=Toast.makeText(getApplicationContext(),"Quiz name exists",Toast.LENGTH_LONG);
                    popup.show();
                }
            }
        });
        Button createButton = findViewById(R.id.createQuizButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Quiz.checkQuizNameUnique(quizNameInput.getText().toString())){
                try {
                    if(((EditText) findViewById(R.id.quizName)).getText().length()==0){
                        Toast popup=Toast.makeText(getApplicationContext(),"Enter quiz name.",Toast.LENGTH_LONG);
                        popup.show();
                        throw new Exception();
                    }
                    if(((EditText) findViewById(R.id.quizDescription)).getText().length()==0){
                        Toast popup=Toast.makeText(getApplicationContext(),"Enter quiz description.",Toast.LENGTH_LONG);
                        popup.show();
                        throw new Exception();
                    }
                    if(((EditText) findViewById(R.id.numberQuizWords)).getText().length()==0){
                        Toast popup=Toast.makeText(getApplicationContext(),"Enter number of quiz words.",Toast.LENGTH_LONG);
                        popup.show();
                        throw new Exception();
                    }
                    String quizName = ((EditText) findViewById(R.id.quizName)).getText().toString();
                    String quizDescription = ((EditText) findViewById(R.id.quizDescription)).getText().toString();
                    int numberOfString = Integer.parseInt(((EditText) findViewById(R.id.numberQuizWords)).getText().toString());
                    Quiz temp = new Quiz(quizName, quizDescription, numberOfString);
                    LoginRegisterActivity.quizMap.put(quizName, temp);
                    LoginRegisterActivity.applicationBundle.putString("quizAdding", quizName);
                    LoginRegisterActivity.applicationBundle.putInt("wordAdded", 0);
                    Intent Convert = new Intent(getApplicationContext(), AddQuizWordActivity.class);
                    startActivity(Convert);

                }
                catch(Exception e){
                    //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
                    //popup.show();
                }}

            }
        });
    }
}
