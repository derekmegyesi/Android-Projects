package com.dmegyesi.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RemoveQuizActivity extends AppCompatActivity  implements Serializable {
    public String selected="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_quiz);
    }
//    protected void onResume()
//    {
//        super.onResume();
//        setContentView(R.layout.activity_remove_quiz);
//        try {
//            Spinner spin = (Spinner) findViewById(R.id.removeQuizMultiSelect);
//            ArrayAdapter<String> AD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, LoginRegisterActivity.studentMap.get(LoginRegisterActivity.loggedInUser).quizCreated);
//            AD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spin.setAdapter(AD);
//            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                    Spinner spin2 = (Spinner) findViewById(R.id.removeQuizMultiSelect);
//                    //Quiz.removeQuiz(parent.getItemAtPosition(position).toString());
//                    Toast popup=Toast.makeText(getApplicationContext(),LoginRegisterActivity.loggedInUser,1);
//                    popup.show();
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });
//        }catch(Exception e){
//            Toast popup=Toast.makeText(getApplicationContext(),e.getMessage().toString().toLowerCase(),1);
//            popup.show();
//        }
//    }
    @Override
    protected void onStart(){
        super.onStart();
        setContentView(R.layout.activity_remove_quiz);
        Button backButton = findViewById(R.id.confirmButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                Quiz.removeQuiz(selected);
                Intent Convert = new Intent(getApplicationContext(), QuizMainMenuActivity.class);
                startActivity(Convert);}
                catch(Exception e){
                    //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
                    //popup.show();
                }
            }
        });
        try {
            Spinner spin = findViewById(R.id.removeQuizMultiSelect);
            ArrayAdapter<String> AD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, LoginRegisterActivity.studentMap.get(LoginRegisterActivity.loggedInUser).quizCreated);
            AD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(AD);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Spinner spin2 = findViewById(R.id.removeQuizMultiSelect);
                    selected=parent.getItemAtPosition(position).toString();
                    //Toast popup=Toast.makeText(getApplicationContext(),LoginRegisterActivity.loggedInUser,1);
                    //popup.show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }catch(Exception e){
            //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage().toString().toLowerCase(),1);
            //popup.show();
        }

    }
    @Override
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
    }
}
