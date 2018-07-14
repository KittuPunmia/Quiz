package com.kittu.quiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MAinActivity" ;
    ProgressDialog progressDialog;
    RecyclerView recyclerview;
    DrawerLayout drawerLayout;
    Toolbar toolBar;
    ActionBarDrawerToggle toggle;
    NavigationView navigationview;
    int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading Quiz");
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
*/
        setUpToolbar();
        get_quiz_question();
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
                        Toast.makeText(MainActivity.this, "HOME", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.QuizAttempted:

                        Intent i=new Intent(MainActivity.this,QuizAttempted.class);
                        startActivity(i);
                        //Toast.makeText(MainActivity.this, "QuizAttempted", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_aboutuse:
                        Toast.makeText(MainActivity.this, "about us", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sf = getSharedPreferences("access_token", MODE_PRIVATE);
        String token = sf.getString("value", null);
        if (token == null) {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();

        }

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

 /*   private void get_quiz(){
Call<List<List<Quiz>>> quizlist=ApiClient.getApiClient().getquiz();
quizlist.enqueue(new Callback <List<List<Quiz>>>() {
    @Override
    public void onResponse(Call <List<List<Quiz>>> call, retrofit2.Response<List<List<Quiz>>> response) {
        List<List<Quiz>> list=response.body();
       // recyclerview.setAdapter(new QuizAdapter(MainActivity.this,list));
        Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
progressDialog.dismiss();

     //   Log.i(TAG, "onResponse: "+list.get(1).getQuizquestion());

    }


    @Override
    public void onFailure(Call<List<List<Quiz>>> call, Throwable t) {
        Log.i(TAG, "onFailure: "+t.getMessage());
        progressDialog.dismiss();
        Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();

    }
});

    }
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void get_quiz_question() {
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
final ArrayList<Quizlist> a=new ArrayList<>();
                        SharedPreferences sf = getSharedPreferences("access_token", MODE_PRIVATE);
                        String token = sf.getString("value", null);

                        //Map<String,String> marksmap=new HashMap<String, String>();
                        //marksmap.put("access_token",token);

                        String url= "http://2a58beba.ngrok.io/quizApp/quizid.php/";
                        JSONObject jsonParam1 =new JSONObject();
                        try {
                            jsonParam1.put("access_token",token);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONArray array=new JSONArray();
                        array.put(jsonParam1);


                        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, url,array, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                int count = 0;
                                while (count < response.length()) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(count);
                                      // recyclerview.setAdapter(new QuizAdapter(MainActivity.this,a));
                               Quizlist quizdata=new Quizlist(jsonObject.getString("quiz_id"),jsonObject.getString("quiz_questions_no"));
                           a.add(quizdata);
                                        Log.i("hey", "onResponse: " + jsonObject.toString());
                                       // progressDialog.dismiss();
                                        count++;
                                    } catch (JSONException e) {
                                 e.printStackTrace();
                               //  progressDialog.dismiss();
                                    }

                                }
                                recyclerview.setAdapter(new QuizAdapter(MainActivity.this,a));

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i(TAG, "onErrorResponse: "+error.getMessage());
                            }
                        });
                        Singleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);

                    }
                }, 3000);
    }

    }


