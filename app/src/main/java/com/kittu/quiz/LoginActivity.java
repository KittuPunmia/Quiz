package com.kittu.quiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.college_id)
    EditText college_id;
    @BindView(R.id.Password)
    EditText Password;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }


    public void login() {
        Log.d("LOGIN ACTIVITY", "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        btnSignIn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String id = college_id.getText().toString();
        final String password = Password.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        // String url = "http://localhost/quizapp/token.php";
                        String url = "http://192.168.29.198/quizapp/token.php";

                        Map<String, String> params = new HashMap<String, String>();
                        // params.put("email", email);
                        params.put("client_id", id);
                        params.put("client_secret", password);
                        params.put("grant_type", "client_credentials");

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i("response",response.toString());
                                try {
                                    String tokstr = response.getString("access_token");
                                    SharedPreferences loginState =getSharedPreferences("access_token",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = loginState.edit();
                                    editor.putString("value", tokstr);
                                    editor.commit();

                                    progressDialog.dismiss();
                                    Intent i1 = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(i1);
                                    return;
                                } catch (JSONException e) {
                                    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                    btnSignIn.setEnabled(true);
                                    progressDialog.dismiss();

                                    return;
                                }

                               // Log.i(TAG, "onResponse: " + response.toString());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                               Log.i(TAG, "onErrorResponse: " + error.getCause());
                                VolleyLog.e("Error: ", error.getMessage());
                                progressDialog.dismiss();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String,String> map = new HashMap<String, String>();
                                map.put("client_id", id);
                                map.put("client_secret", password);
                                map.put("grant_type", "client_credentials");

                                return map;
                            }
                        };
                        Singleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
                        // Volley.newRequestQueue(LoginActivity.this).add(jsonRequest);
                        //onSignupSuccess();
                        // onSignupFailed();
                     //   progressDialog.dismiss();

                    }
                }, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }



    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed in validation", Toast.LENGTH_LONG).show();

        btnSignIn.setEnabled(true);
    }


    public boolean validate() {
        boolean valid = true;

        String id = college_id.getText().toString();
        String password = Password.getText().toString();

        if (id.isEmpty() || id.length()< 3) {
            college_id.setError("Enter a valid college id");
            valid = false;
        } else {
            college_id.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 50) {
            Password.setError("Between 7 and 50 alphanumeric characters");
            valid = false;
        } else {
            Password.setError(null);
        }

        return valid;
    }

}
