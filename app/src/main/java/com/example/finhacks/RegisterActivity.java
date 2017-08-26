package com.example.finhacks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by asus on 8/24/2017.
 */
public class RegisterActivity extends AppCompatActivity {
    private EditText inputName, inputEmail, inputUsername, inputPIN;
    private Button registerButton;

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
}
