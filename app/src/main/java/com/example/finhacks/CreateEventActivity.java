package com.example.finhacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by asus on 8/26/2017.
 */
public class CreateEventActivity extends AppCompatActivity {
    private EditText inputEventName, inputEventDescription, inputEventDeadline, inputTargetBalance;
    private Button createButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        //Edit Text
        inputEventName = (EditText) findViewById(R.id.input_event_name);
        inputEventDescription = (EditText) findViewById(R.id.input_event_description);
        inputEventDeadline = (EditText) findViewById(R.id.input_event_deadline);
        inputTargetBalance = (EditText) findViewById(R.id.input_target_balance);
        createButton = (Button) findViewById(R.id.create_event_btn);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEventName()) {
                    return;
                } else if (!validateEventDescription()) {
                    return;
                } else if (!validateEventDeadline()) {
                    return;
                } else if (!validateTargetBalance()) {
                    return;
                }
            }
        });
    }

    private boolean validateEventName() {
        if (inputEventName.getText().toString().trim().isEmpty()) {
            inputEventName.setError(getString(R.string.err_msg_empty));
            requestFocus(inputEventName);
            return false;
        }
        return true;
    }

    private boolean validateEventDescription() {
        if (inputEventDescription.getText().toString().trim().isEmpty()) {
            inputEventDescription.setError(getString(R.string.err_msg_empty));
            requestFocus(inputEventDescription);
            return false;
        }
        return true;
    }

    private boolean validateEventDeadline() {
        if (inputEventDeadline.getText().toString().trim().isEmpty()) {
            inputEventDeadline.setError(getString(R.string.err_msg_empty));
            requestFocus(inputEventDeadline);
            return false;
        }
        return true;
    }

    private boolean validateTargetBalance() {
        String balance = inputTargetBalance.getText().toString().trim();
        if (balance.isEmpty() || balance.length() <= 100000) {
            inputTargetBalance.setError(getString(R.string.err_msg_pin));
            requestFocus(inputTargetBalance);
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
