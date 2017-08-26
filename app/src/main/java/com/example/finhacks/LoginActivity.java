package com.example.finhacks;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by asus on 8/25/2017.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText inputUsername, inputPIN;
    private Button loginButton;
    private TextView registerText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = (EditText) findViewById(R.id.input_username);
        inputPIN = (EditText) findViewById(R.id.input_pin);
        loginButton = (Button) findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!validateUsername()) {
//                    return;
//                } else if (!validatePIN()) {
//                    return;
//                }
                Intent intentHome = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentHome);
            }
        });
        registerText = (TextView) findViewById(R.id.register_link);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
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
}
