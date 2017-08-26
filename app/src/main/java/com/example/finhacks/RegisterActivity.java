package com.example.finhacks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 8/24/2017.
 */
public class RegisterActivity extends AppCompatActivity {
    private EditText inputName, inputEmail, inputUsername, inputPIN;
    private String name, email, username, PIN;
    private Button registerButton;

    RequestQueue requestQueue;
    String POST_DATA = "http://192.168.43.86:8000/api/bca/createUser";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputUsername = (EditText) findViewById(R.id.input_username);
        inputPIN = (EditText) findViewById(R.id.input_pin);

        registerButton = (Button) findViewById(R.id.register_btn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName()) {
                    return;
                } else if (!validateEmailAddress()) {
                    return;
                } else if (!validateUsername()) {
                    return;
                } else if (!validatePIN()) {
                    return;
                }
                name = inputName.getText().toString();
                username = inputUsername.getText().toString();
                email = inputEmail.getText().toString();
                PIN = inputPIN.getText().toString();
                POST_REGISTER_DATA();
            }
        });
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputName.setError(getString(R.string.err_msg_empty));
            requestFocus(inputName);
            return false;
        }
        return true;
    }

    private boolean validateEmailAddress() {
        if (inputEmail.getText().toString().trim().isEmpty()) {
            inputEmail.setError(getString(R.string.err_msg_empty));
            requestFocus(inputEmail);
            return false;
        }
        return true;
    }

    private boolean validateUsername() {
        if (inputUsername.getText().toString().trim().isEmpty()) {
            inputUsername.setError(getString(R.string.err_msg_empty));
            requestFocus(inputUsername);
            return false;
        }
        return true;
    }

    private boolean validatePIN() {
        String PIN = inputPIN.getText().toString().trim();
        if (PIN.isEmpty() || PIN.length() != 6) {
            inputPIN.setError(getString(R.string.err_msg_pin));
            requestFocus(inputPIN);
            return false;
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void POST_REGISTER_DATA(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("onResponse", "UPDATE_ORDER_STATUS");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("update error", "Error.." + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("username", username);
                params.put("email", email);
                params.put("pin", PIN);

                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
