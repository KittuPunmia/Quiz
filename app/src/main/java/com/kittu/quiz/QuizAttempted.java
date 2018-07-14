package com.kittu.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class QuizAttempted extends AppCompatActivity {
    private static final String TAG = "QuizAttempted";
    RecyclerView recyclerview;
    DrawerLayout drawerLayout;
    Toolbar toolBar;
    ActionBarDrawerToggle toggle;
    NavigationView navigationview;
    int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_attempted);
        setUpToolbar();
        get_attempted();
        recyclerview=findViewById(R.id.recyclerview);

        SharedPreferences sf = getSharedPreferences("access_token", MODE_PRIVATE);
        String token = sf.getString("value", null);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerview.getContext(),
                linearLayoutManager.getOrientation());
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addItemDecoration(dividerItemDecoration);
        setRequestedOrientation(orientation);


        navigationview= (NavigationView) findViewById(R.id.navigation_menu);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent i=new Intent(QuizAttempted.this,MainActivity.class);
                        startActivity(i);
                        Toast.makeText(QuizAttempted.this, "HOME", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.QuizAttempted:

                    //    Intent i=new Intent(MainActivity.this,QuizAttempted.class);
                     //   startActivity(i);
                        Toast.makeText(QuizAttempted.this, "QuizAttempted", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(QuizAttempted.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_aboutuse:
                        Toast.makeText(QuizAttempted.this, "about us", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });

    }
    private void setUpToolbar(){
        drawerLayout=(DrawerLayout) findViewById(R.id.drawerlayout);
        toolBar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_apps_black_24dp.xml);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void get_attempted(){
        SharedPreferences sf =getSharedPreferences("access_token", MODE_PRIVATE);
        String token = sf.getString("value", null);

        Map<String,String> marksmap=new HashMap<String, String>();
        marksmap.put("access_token",token);
        Call <List<StudentAttemptedquiz>> quizattemptedlist=ApiClient.getApiClient().get_attempted_quiz(marksmap);
        quizattemptedlist.enqueue(new Callback<List<StudentAttemptedquiz>>() {
            @Override
            public void onResponse(Call <List<StudentAttemptedquiz>> call, retrofit2.Response<List<StudentAttemptedquiz>> response) {
                List <StudentAttemptedquiz> list=response.body();
                Log.i(TAG, "onResponse: "+list.get(0).getQuizId());
               // Intent i=getIntent();
               // int pos=i.getIntExtra("Pos",0);
                //QuizActivity.SamplePageAdapter sp=new QuizActivity.SamplePageAdapter(getSupportFragmentManager(), (List<Quiz>) list.get(pos));
                 recyclerview.setAdapter(new QuizAttemptedAdapter(QuizAttempted.this,list));
                // Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();

                //   Log.i(TAG, "onResponse: "+list.get(1).getQuizquestion());

            }


            @Override
            public void onFailure(Call<List<StudentAttemptedquiz>> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
                //Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();

            }
        });

    }

}
