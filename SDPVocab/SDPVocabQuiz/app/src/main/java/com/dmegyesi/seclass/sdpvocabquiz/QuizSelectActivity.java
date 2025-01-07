package com.dmegyesi.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizSelectActivity extends AppCompatActivity  implements Serializable {
public String selected = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_select);
    }
    @Override
    protected void onStart(){
        super.onStart();
        setContentView(R.layout.activity_quiz_select);
        Button backButton = findViewById(R.id.takeQuizButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (selected!=""){
                    LoginRegisterActivity.applicationBundle.putString("theQuiz",selected);
                    Intent Convert = new Intent(getApplicationContext(), TakeQuizActivity.class);
                    startActivity(Convert);}
                else{
                        Toast popup=Toast.makeText(getApplicationContext(),"Please select a quiz",Toast.LENGTH_LONG);
                        popup.show();
                    }

                }
                catch(Exception e){
                    //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
                    //popup.show();
                }
            }
        });
        ArrayList<String> indexArray=new ArrayList<String>(LoginRegisterActivity.quizMap.keySet());
        for (String T:LoginRegisterActivity.studentMap.get(LoginRegisterActivity.loggedInUser).quizCreated){
            if (indexArray.contains(T)){
                indexArray.remove(T);
            }
        }
        Spinner spin = findViewById(R.id.SelectQuizMultiSelect);
        ArrayAdapter<String> AD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,indexArray);
        AD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(AD);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spin2 = findViewById(R.id.SelectQuizMultiSelect);
                selected=parent.getItemAtPosition(position).toString();
                //Toast popup=Toast.makeText(getApplicationContext(),selected,1);
                //popup.show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
