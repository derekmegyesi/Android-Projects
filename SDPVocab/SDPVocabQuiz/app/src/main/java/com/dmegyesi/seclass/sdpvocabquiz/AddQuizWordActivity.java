package com.dmegyesi.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class AddQuizWordActivity extends AppCompatActivity  implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz_word);

    }
    @Override
    protected void onStart(){
        super.onStart();
        setContentView(R.layout.activity_add_quiz_word);
        Button add = findViewById(R.id.addWordButton);
        if (LoginRegisterActivity.applicationBundle.getInt("wordAdded") == LoginRegisterActivity.quizMap.get(LoginRegisterActivity.applicationBundle.getString("quizAdding")).predefNumberOfWord-1) {
            add.setText(R.string.finish);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String quizAdding = LoginRegisterActivity.applicationBundle.getString("quizAdding");
                try {


                    String word = ((EditText) findViewById(R.id.wordName)).getText().toString();
                    String correctDef = ((EditText) findViewById(R.id.correctDefinition)).getText().toString();
                    String wrongDef1 = ((EditText) findViewById(R.id.incorrectDefinition1)).getText().toString();
                    String wrongDef2 = ((EditText) findViewById(R.id.incorrectDefinition2)).getText().toString();
                    String wrongDef3 = ((EditText) findViewById(R.id.incorrectDefinition3)).getText().toString();

                    if(((EditText) findViewById(R.id.wordName)).getText().length()==0){
                        Toast popup=Toast.makeText(getApplicationContext(),"Enter quiz word.",Toast.LENGTH_LONG);
                        popup.show();
                        throw new Exception();
                    }
                    if(((EditText) findViewById(R.id.correctDefinition)).getText().length()==0){
                        Toast popup=Toast.makeText(getApplicationContext(),"Enter correct definition.",Toast.LENGTH_LONG);
                        popup.show();
                        throw new Exception();
                    }
                    if(((EditText) findViewById(R.id.incorrectDefinition1)).getText().length()==0||((EditText) findViewById(R.id.incorrectDefinition2)).getText().length()==0||((EditText) findViewById(R.id.incorrectDefinition3)).getText().length()==0){
                        Toast popup=Toast.makeText(getApplicationContext(),"Enter incorrect definitions",Toast.LENGTH_LONG);
                        popup.show();
                        throw new Exception();
                    }
                    int wordAdded = LoginRegisterActivity.applicationBundle.getInt("wordAdded");
                    wordAdded++;
                    LoginRegisterActivity.applicationBundle.putInt("wordAdded", wordAdded);
                    LoginRegisterActivity.quizMap.get(quizAdding).buildWordOptions(word, correctDef, wrongDef1, wrongDef2, wrongDef3);

                    if (LoginRegisterActivity.applicationBundle.getInt("wordAdded") == LoginRegisterActivity.quizMap.get(LoginRegisterActivity.applicationBundle.getString("quizAdding")).predefNumberOfWord) {
                        LoginRegisterActivity.quizMap.get(quizAdding).finishBuildingQuiz();
                        Intent Convert = new Intent(getApplicationContext(), QuizMainMenuActivity.class);
                        startActivity(Convert);

                    } else if (LoginRegisterActivity.applicationBundle.getInt("wordAdded") < LoginRegisterActivity.quizMap.get(LoginRegisterActivity.applicationBundle.getString("quizAdding")).predefNumberOfWord) {
                        Intent Convert = new Intent(getApplicationContext(), AddQuizWordActivity.class);
                        startActivity(Convert);
                    }
                }catch(Exception e){
                    //Toast popup = Toast.makeText(getApplicationContext(), e.getMessage(), 1);
                    //popup.show();
                }

            }
        });
    }
    protected void onPause(){
        super.onPause();
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
