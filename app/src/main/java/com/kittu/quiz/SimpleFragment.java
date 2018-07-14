package com.kittu.quiz;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */

public class SimpleFragment extends Fragment {
    public static int ob=0;
    public SimpleFragment() {
        // Required empty public constructor
       }

    public static SimpleFragment newInstance(Quiz pos) {
        Bundle args = new Bundle();
        args.putParcelable("PHOTO",pos);
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle args=getArguments();
        if(args==null)
        {
            throw new AssertionError();
        }
        final Quiz p=args.getParcelable("PHOTO");
        if(p==null)
        {
            throw new AssertionError();
        }
        final String a= (String) args.get("Title");
        View rootView = inflater.inflate(
                R.layout.fragment_simple, container, false);

        TextView tvQues=rootView.findViewById(R.id.tvQues);
        tvQues.setText(p.getQuizquestion());
        final TextView tvOp1=rootView.findViewById(R.id.tvOp1);
        tvOp1.setText(p.getQuizoption1());
        final TextView tvOp2=rootView.findViewById(R.id.tvOp2);
        tvOp2.setText(p.getQuizoption2());
        final TextView tvOp3=rootView.findViewById(R.id.tvOp3);
        tvOp3.setText(p.getQuizoption3());
        final TextView tvOp4=rootView.findViewById(R.id.tvOp4);
        tvOp4.setText(p.getQuizoption4());
       Log.i("QuizActivity", "onClick: "+ob);

        tvOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(p.getQuizans().equals("1")){
                    tvOp1.setBackgroundResource(R.color.green);
                  //  tvOp1.setBackgroundColor(Color.rgb(0,1,0));
                    ++ob;
                    Log.i("QuizActivity", "onClick: "+ob);


                }else{
                    //tvOp1.setBackgroundColor(Color.rgb(1,0,0));
                    tvOp1.setBackgroundResource(R.color.red);

                }

                tvOp1.setClickable(false);
                tvOp2.setClickable(false);
                tvOp3.setClickable(false);
                tvOp4.setClickable(false);
            }
        });
        tvOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(p.getQuizans().equals("2")){
                 //   tvOp2.setBackgroundColor(Color.rgb(0,1,0));
                    tvOp2.setBackgroundResource(R.color.green);

                    ++ob;
                    Log.i("QuizActivity", "onClick: "+ob);

                }else{
                   // tvOp2.setBackgroundColor(Color.rgb(1,0,0));
                    tvOp2.setBackgroundResource(R.color.red);

                }
                tvOp1.setClickable(false);
                tvOp2.setClickable(false);
                tvOp3.setClickable(false);
                tvOp4.setClickable(false);

            }
        });
        tvOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(p.getQuizans().equals("3")){
                    ++ob;
                    tvOp3.setBackgroundResource(R.color.green);
                    Log.i("QuizActivity", "onClick: "+ob);

                 //   tvOp3.setBackgroundColor(Color.rgb(0,1,0));
                }else{
                    //tvOp3.setBackgroundColor(Color.rgb(1,0,0));
                    tvOp3.setBackgroundResource(R.color.red);

                }
                tvOp1.setClickable(false);
                tvOp2.setClickable(false);
                tvOp3.setClickable(false);
                tvOp4.setClickable(false);


            }
        });
        tvOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(p.getQuizans().equals("4")){
                    tvOp4.setBackgroundResource(R.color.green);

                 //   tvOp4.setBackgroundColor(Color.rgb(0,1,0));
                    ++ob;
                    Log.i("QuizActivity", "onClick: "+ob);
                }else{
                   // tvOp4.setBackgroundColor(Color.rgb(1,0,0));
                    tvOp4.setBackgroundResource(R.color.red);

                }
                tvOp1.setClickable(false);
                tvOp2.setClickable(false);
                tvOp3.setClickable(false);
                tvOp4.setClickable(false);


            }

        });
        final ViewPager pager = (ViewPager) container;
        final int size=pager.getAdapter().getCount();

        final Button button=rootView.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curr=pager.getCurrentItem();
                if(curr==(size-1)){
                    /*SharedPreferences loginState =getContext().getSharedPreferences("access_token",MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginState.edit();

                    editor.putInt("total",total);
                    editor.putInt("obtained",obtained);*/
                    int total=Integer.parseInt(p.getQuizquestionno());
                    String quiz_id=p.getQuizId();
                    Snackbar snackbar=Snackbar.make(pager,"Your Score Is:"+ob+" out of "+total,Snackbar.LENGTH_LONG);

                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();
                    // Toast.makeText(getContext(), "total "+total+"and count"+ ob, Toast.LENGTH_SHORT).show();
                    Log.i("Quiz Activity", "onCreateView: "+total+" "+ ob);
                    String obtained=""+ob;
                    quiz_marks(quiz_id,p.getQuizquestionno(),obtained);
                    //editor.commit();
                    button.setClickable(false);
                    ob=0;

                }else{
                    pager.setCurrentItem(pager.getCurrentItem()+1);

                }

            }
        });

        return rootView;

    }

    private void quiz_marks(final String quiz_id, final String total, final String ob) {

                        final ArrayList<Quizlist> a=new ArrayList<>();
                        String url= "http://2a58beba.ngrok.io/quizApp/quizmarks.php/";

        SharedPreferences sf = getActivity().getSharedPreferences("access_token", MODE_PRIVATE);
        String token = sf.getString("value", null);

                        Map<String,String> marksmap=new HashMap<String, String>();
                        marksmap.put("access_token",token);
                        marksmap.put("quiz_id",quiz_id);
                        marksmap.put("obtained",ob);
                        marksmap.put("total",total);
        Log.i("QuizActivity", "onResponse: "+token+" "+quiz_id+" "+ob+" "+total);


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, new JSONObject(marksmap), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Boolean success=response.getBoolean("success");
                    Log.i("QuizActivity", "onResponse: "+success);
                    Intent i=new Intent(getContext(),MainActivity.class);
                    startActivity(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("QuizActivity" ,"onErrorResponse: "+error.getMessage());
            }
        });
        Singleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }



}
