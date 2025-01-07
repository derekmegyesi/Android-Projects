package com.dmegyesi.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RegisterNewUserActivity extends AppCompatActivity  implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);
        final EditText username= findViewById(R.id.loginUsername);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(Student.checkUniqueUser(username.getText().toString().toLowerCase())){
                    Toast popup=Toast.makeText(getApplicationContext(),"Username already exists", Toast.LENGTH_LONG);
                    popup.show();
                }
            }
        });
        Button registerButton = findViewById(R.id.registerButton);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String userName = ((EditText) findViewById(R.id.loginUsername)).getText().toString().toLowerCase();

                    String major = ((EditText) findViewById(R.id.majorSubject)).getText().toString();
                    String senior = getSeniority();
                    String email = ((EditText) findViewById(R.id.emailAddress)).getText().toString();

                    if(userName.length()==0){
                        Toast popup=Toast.makeText(getApplicationContext(),"Blank username is not allowed", Toast.LENGTH_LONG);
                        popup.show();

                    } else if(major.length()==0){
                        Toast popup=Toast.makeText(getApplicationContext(),"Blank major is not allowed", Toast.LENGTH_LONG);
                        popup.show();

                    } else if(email.length()==0){
                        Toast popup=Toast.makeText(getApplicationContext(),"Blank email address is not allowed", Toast.LENGTH_LONG);
                        popup.show();


                    } else if(!Student.checkUniqueUser(userName)) {
                        Student temp = new Student();
                        temp.register(userName, major, senior, email);
                        Toast popup=Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_LONG);
                        popup.show();
                        Intent Convert = new Intent(getApplicationContext(),LoginRegisterActivity.class);
                        startActivity(Convert);

                    } else if(Student.checkUniqueUser(userName)){
                        Toast popup=Toast.makeText(getApplicationContext(),"Username exists, use another one.", Toast.LENGTH_LONG);
                        popup.show();
                    }
                }
                catch(Exception e){
                    //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage().toString().toLowerCase(),1);
                    //popup.show();
                }
            }
        });
    }
    public String getSeniority(){
        RadioGroup senioritySel = findViewById(R.id.seniorityLevelRadioGroup);
        int select = senioritySel.getCheckedRadioButtonId();
        String temp ="";
        switch(select) {
            case R.id.freshmanRadioButton:
                temp= "Freshman";
                break;
            case R.id.sophomoreRadioButton:
                temp= "Sophomore";
                break;
            case R.id.juniorRadioButton:
                temp= "Junior";
                break;
            case R.id.seniorRadioButton:
                temp= "Senior";
                break;
            case R.id.gradRadioButton:
                temp= "Grad";
                break;
        }
        return temp;
    }
    @Override
    protected void onPause(){
        super.onPause();
        try{
            FileOutputStream os_Student = openFileOutput("student", MODE_PRIVATE);
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
    }

}
