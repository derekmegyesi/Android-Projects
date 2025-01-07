package com.dmegyesi.seclass.sdpvocabquiz;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class LoginRegisterActivity extends AppCompatActivity implements Serializable {
    public static Bundle applicationBundle;
    public static String loggedInUser;
    public static String loggedInUserForDisplay;
    public static HashMap<String,Quiz> quizMap = new HashMap<>();
    public static HashMap<String,Student>studentMap = new HashMap<>();
    public static HashMap<String,HashMap<String,QuizResult>>resultMap=new HashMap<String,HashMap<String,QuizResult>>();

    @Override
    protected void onStart(){
        super.onStart();


        try{
            FileInputStream os_Student = openFileInput("student");
            ObjectInputStream oo_Student = new ObjectInputStream(os_Student);
            try {
                //Toast popup=Toast.makeText(getApplicationContext(),((HashMap)oo_Student.readObject()).isEmpty(),1);
                //popup.show();
                studentMap = (HashMap) oo_Student.readObject();
                //Toast popup=Toast.makeText(getApplicationContext(),"Read Student. Size is "+studentMap.size(),1);
                //popup.show();
                os_Student.close();
                oo_Student.close();
            }catch(ClassNotFoundException c){
                //Toast popup=Toast.makeText(getApplicationContext(),"Restore student"+c.getMessage(),1);
                //popup.show();
            }
        } catch (Exception e) {
            //Toast popup=Toast.makeText(getApplicationContext(),e.toString(),1);
            //popup.show();
        }finally {

        }
        try{
            FileInputStream os_quiz = openFileInput("quiz");
            ObjectInputStream oo_quiz = new ObjectInputStream(os_quiz);
            try {
                quizMap = (HashMap) oo_quiz.readObject();
                //Toast popup=Toast.makeText(getApplicationContext(),"Read Quiz. Size is "+quizMap.size(),1);
                //popup.show();
                os_quiz.close();
                oo_quiz.close();
            }catch(ClassNotFoundException c){
                //Toast popup=Toast.makeText(getApplicationContext(),c.getMessage(),1);
                //popup.show();
            }
        } catch (Exception e) {
            //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
            //popup.show();
        }
        try{
            FileInputStream os_result = openFileInput("result");
            ObjectInputStream oo_result = new ObjectInputStream(os_result);

            try {
                resultMap = (HashMap)oo_result.readObject();
                oo_result.close();
                os_result.close();
            }catch(Exception c){
                //Toast popup=Toast.makeText(getApplicationContext(),c.getMessage(),1);
                //popup.show();
            }
        } catch (Exception e) {
            //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
            //popup.show();
        }
        TextView registered = findViewById(R.id.registeredUsers);
        registered.setText(studentMap.size()+" students registered");
        TextView created = findViewById(R.id.createdQuizzes);
        created.setText(quizMap.size()+" quizzes created");
        TextView result = findViewById(R.id.quizResults);
        result.setText(resultMap.size()+" results");



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        applicationBundle = new Bundle();
        if (savedInstanceState!=null) {
            applicationBundle = savedInstanceState;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        Button loginButton= findViewById(R.id.loginButton);
        final EditText inputUsername= findViewById((R.id.loginUsername));

        /*try {
            Student NS=new Student();
            NS.register("jc","Major","Senior","cchen684@gatech.edu");


        }
        catch(Exception e){
            //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage().toString().toLowerCase(),1);
            //popup.show();
        }*/

        //inputUsername.setText("JC");
        loginButton.setOnClickListener(new View.OnClickListener() {
            EditText inputUsernameInner=(EditText)findViewById((R.id.loginUsername));

            @Override
            public void onClick(View v) {


                if (LoginRegisterActivity.studentMap.containsKey(inputUsernameInner.getText().toString().toLowerCase())) {
                    Intent Convert = new Intent(getApplicationContext(), QuizMainMenuActivity.class);
                    LoginRegisterActivity.loggedInUserForDisplay= inputUsername.getText().toString();
                    LoginRegisterActivity.loggedInUser= loggedInUserForDisplay.toLowerCase();


                    startActivity(Convert);
                    overridePendingTransition(0,0);

                }else{
                    Toast popup=Toast.makeText(getApplicationContext(),"Username doesn't exist. Register first",Toast.LENGTH_LONG);
                    popup.show();
                }

            }

        });

        Button Reg= findViewById(R.id.registerButton);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Convert = new Intent(getApplicationContext(),RegisterNewUserActivity.class);
                startActivity(Convert);
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        try{
            FileOutputStream os_Student = openFileOutput("student",MODE_PRIVATE);
            ObjectOutputStream oo_Student = new ObjectOutputStream(os_Student);
            oo_Student.writeObject(studentMap);
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
                oo_quiz.writeObject(quizMap);

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
                oo_result.writeObject(resultMap);
                //Toast popup=Toast.makeText(getApplicationContext(),"Save Result",1);
                //popup.show();
                oo_result.close();
                os_result.close();
            }catch(Exception f){
                Toast popup=Toast.makeText(getApplicationContext(),f.getMessage(),Toast.LENGTH_LONG);
                popup.show();
            }
        } catch (Exception e) {
            //Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
            //popup.show();
        }
        //Create Test Quiz
/*            Quiz NQ = new Quiz("Good1","HAHAHAHA",3);
            NQ.buildWordOptions("Word", "Def1_Correct", "Def2", "Def3", "Def4");
            NQ.buildWordOptions("Word2", "Def11_Correct", "Def22", "Def33", "Def44");
            NQ.finishBuildingQuiz();
            Quiz NQ2 = new Quiz("Good2","HAHAHAHA",3);
            NQ2.buildWordOptions("Word", "Def1_Correct", "Def2", "Def3", "Def4");
            NQ2.buildWordOptions("Word2", "Def11_Correct", "Def22", "Def33", "Def44");
            NQ2.finishBuildingQuiz();*/
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();

    }

}
