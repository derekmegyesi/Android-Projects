package com.dmegyesi.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import java.io.Serializable;

public class QuizMainMenuActivity extends AppCompatActivity  implements Serializable {
    @Override
    public void onBackPressed(){

}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main_menu);
        final RadioGroup QuizRadioGroup = findViewById(R.id.QuizMainMenuRadioGroup);
        Button confirm = findViewById(R.id.confirmButton);
        confirm.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int check=QuizRadioGroup.getCheckedRadioButtonId();
        switch(check) {
            case R.id.practiseQuizRadioButton:
                Intent Convert = new Intent(getApplicationContext(), QuizSelectActivity.class);
                startActivity(Convert);
                break;
            case R.id.addQuizRadioButton:
                Intent Convert2 = new Intent(getApplicationContext(), CreateNewQuizActivity.class);
                startActivity(Convert2);
                break;
            case R.id.removeQuizRadioButton:
                Intent Convert3 = new Intent(getApplicationContext(), RemoveQuizActivity.class);
                startActivity(Convert3);
                break;
            case R.id.viewQuizScoreStatsRadioButton:
                Intent Convert4 = new Intent(getApplicationContext(), StatsForStudentActivity.class);
                startActivity(Convert4);
                break;
        }
    }
});
        Button logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Convert2 = new Intent(getApplicationContext(), LoginRegisterActivity.class);
                startActivity(Convert2);
            }
        });
        }
    }

