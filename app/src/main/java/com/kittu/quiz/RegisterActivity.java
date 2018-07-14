package com.kittu.quiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG ="RegisterActivity" ;
    EditText collegetoken;
EditText Password;
EditText Password2;
EditText college_id;
Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        collegetoken=findViewById(R.id.collegetoken);
        Password2=findViewById(R.id.Password2);
        Password=findViewById(R.id.Password);
        college_id=findViewById(R.id.college_id);
        btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
    }
    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            Log.d("??","wtf");
            onSignupFailed();
            return;
        }

        btnRegister.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String id = college_id.getText().toString();
        final String token = collegetoken.getText().toString();
        final String password = Password.getText().toString();
        final String reEnterPassword = Password2.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        String url = "http://192.168.29.198/quizapp/register.php";
                        Map<String, String> params= new HashMap<String, String>();
                        params.put("accountid", id);
                        // params.put("email", email);
                        params.put("password", password);
                        params.put("token", token);

                        JsonObjectRequest jsonRequest = new JsonObjectRequest
                                (Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // the response is already constructed as a JSONObject!
                                        Log.i("response",response.toString());
                                        try {
                                            response.getBoolean("success");
                                            progressDialog.dismiss();
                                            Intent i1 = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(i1);
                                            return;
                                        } catch (JSONException e) {
                                            Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();
                                            progressDialog.dismiss();
                                            btnRegister.setEnabled(true);
                                            return;
                                        }
                                    }
                                }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();
                                        btnRegister.setEnabled(true);
                                        Log.i("error",error.getMessage());
                                        return;
                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();

                                return params;
                            }
                        };

                        Volley.newRequestQueue(RegisterActivity.this).add(jsonRequest);
                        //onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        btnRegister.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();

        btnRegister.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String collegeid = college_id.getText().toString();
        String token = collegetoken.getText().toString();
        String password = Password.getText().toString();
        String reEnterPassword = Password2.getText().toString();

        if (collegeid.isEmpty() || collegeid.length() < 3) {
            college_id.setError("Enter Valid College ID");
            valid = false;
        } else {
            college_id.setError(null);
        }


        if (token.isEmpty()) {
            collegetoken.setError("Enter Valid Token Number");
            valid = false;
        } else {
            collegetoken.setError(null);
        }

        if (password.isEmpty() || password.length() < 7 || password.length() > 50) {
            Password.setError("between 7 and 50 alphanumeric characters");
            valid = false;
        } else {
            Password2.setError(null);
        }

        if (reEnterPassword.isEmpty() || !(reEnterPassword.equals(password))) {
            Password2.setError("Password Do not match");
            valid = false;
        } else {
            Password2.setError(null);
        }

        return valid;
    }
}
