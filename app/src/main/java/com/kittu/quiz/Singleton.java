package com.kittu.quiz;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singleton {

    private static Singleton mInstance;
    private RequestQueue requestQueue;
    private static Context mctx;
    private Singleton(Context context){

        mctx=context;
        requestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue() {

        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());

        }
        return requestQueue;
    }
    public  static synchronized Singleton getInstance(Context context){
        if(mInstance==null){
            mInstance=new Singleton(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
