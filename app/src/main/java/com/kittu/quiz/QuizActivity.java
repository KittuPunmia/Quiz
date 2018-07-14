package com.kittu.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG ="QUizActivity" ;
    ViewPager vw;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        vw=findViewById(R.id.vw);
        toolbar=findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("QUIZ");

    }


    private void get_quiz(){
        SharedPreferences sf = getSharedPreferences("access_token", MODE_PRIVATE);
        String token = sf.getString("value", null);

        Map<String,String> marksmap=new HashMap<String, String>();
        marksmap.put("access_token",token);

        Call<List<List<Quiz>>> quizlist=ApiClient.getApiClient().getquiz(marksmap);
        quizlist.enqueue(new Callback <List<List<Quiz>>>() {
            @Override
            public void onResponse(Call <List<List<Quiz>>> call, retrofit2.Response<List<List<Quiz>>> response) {
                List<List<Quiz>> list=response.body();
                Intent i=getIntent();
              int pos=i.getIntExtra("Pos",0);
                SamplePageAdapter sp=new SamplePageAdapter(getSupportFragmentManager(), (List<Quiz>) list.get(pos));
                vw.setAdapter(sp);

                //vw.setPageTransformer(true, new CubeOutDepthTransformation());
                vw.setCurrentItem(0);

                // recyclerview.setAdapter(new QuizAdapter(MainActivity.this,list));
               // Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
               // progressDialog.dismiss();

                //   Log.i(TAG, "onResponse: "+list.get(1).getQuizquestion());

            }


            @Override
            public void onFailure(Call<List<List<Quiz>>> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
               // progressDialog.dismiss();
                //Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();

            }
        });

    }

  /*  public void jumpToPage(View view) {
        vw.setCurrentItem(vw.getCurrentItem()+1);
    }
*/
    private class SamplePageAdapter extends FragmentStatePagerAdapter {

        private List<Quiz> p;
        public SamplePageAdapter(FragmentManager fm, List<Quiz> p) {
            super(fm);
            this.p=p;
        }

        @Override
        public Fragment getItem(int position) {
                return SimpleFragment.newInstance(p.get(position));
              /*  Toast.makeText(QuizActivity.this, "Quiz Over", Toast.LENGTH_SHORT).show();
                SharedPreferences sf = getSharedPreferences("access_token", MODE_PRIVATE);
int total=sf.getInt("total",0);
int obtained=sf.getInt("obtained",0);
Snackbar.make(vw,"Your Score Is:"+obtained+"out of"+total,Snackbar.LENGTH_LONG);
*/

        }

        @Override
        public int getCount() {
            return p.size();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        get_quiz();

    }
}

