package com.dmegyesi.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StatsForStudentActivity extends AppCompatActivity  implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_for_student);
        TextView statsForStudent = findViewById(R.id.statsForStudentBanner);
        statsForStudent.setText("Statistics for " + LoginRegisterActivity.loggedInUserForDisplay);
    }

    @Override
    protected void onStart(){
        PopupWindow pw = new PopupWindow(this);
        pw.showAtLocation(findViewById(R.id.returnMainMenuLayout), Gravity.CENTER,10,10);
        try {
            ArrayList<QuizResult> testsTakenByUser = new ArrayList<QuizResult>();
            ArrayList<String> testNames = new ArrayList<String>();
            ArrayList<String> otherTests = new ArrayList<String>();
            super.onStart();
            for (String i : LoginRegisterActivity.studentMap.get(LoginRegisterActivity.loggedInUser).quizTaken) {

                testsTakenByUser.add(LoginRegisterActivity.resultMap.get(i).get(LoginRegisterActivity.loggedInUser));
            }
            Collections.sort(testsTakenByUser, new Comparator<QuizResult>() {
                @Override
                public int compare(QuizResult o1, QuizResult o2) {
                    return o1.latestDate.compareTo(o2.latestDate);
                }
            });
            for (QuizResult i : testsTakenByUser) {
                if (!testNames.contains(i.testName)) {
                    testNames.add(i.testName);
                }
            }
            Collections.reverse(testNames);
            for (String i : LoginRegisterActivity.resultMap.keySet()) {
                if (testNames.contains(i)) {
                } else {
                    otherTests.add(i);
                }
            }
            //Toast popup=Toast.makeText(getApplicationContext(),"The size of the resultMap is "+LoginRegisterActivity.resultMap.size(),1);
            //popup.show();
            testNames.addAll(otherTests);
            Spinner getTestsSpinner = (Spinner) findViewById(R.id.quizzesPlayedByUsernameMultiSelect);
            ArrayAdapter<String> AD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, testNames);
            AD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            getTestsSpinner.setAdapter(AD);
            getTestsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selected = parent.getItemAtPosition(position).toString();
                    LoginRegisterActivity.applicationBundle.putString("getStats", selected);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            Button viewStats = (Button) findViewById(R.id.viewStatisticsButton);

            viewStats.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 if(LoginRegisterActivity.applicationBundle.getString("getStats")!=null){
                                                 Intent Convert = new Intent(getApplicationContext(), StatsForQuizActivity.class);
                                                 startActivity(Convert);
                                             }
                                         }
            }
            );
            Button back = (Button) findViewById(R.id.returnMainMenuButton);
            back.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent Convert = new Intent(getApplicationContext(), QuizMainMenuActivity.class);
                                            startActivity(Convert);
                                        }
                                    }
            );
        }catch (Exception e){
           // Toast popup=Toast.makeText(getApplicationContext(),e.getMessage(),1);
            //popup.show();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
