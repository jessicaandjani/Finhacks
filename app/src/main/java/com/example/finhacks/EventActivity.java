package com.example.finhacks;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 8/26/2017.
 */
public class EventActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private List<Event> eventList = new ArrayList<Event>();
    private ListView listView;
    private EventListAdapter adapter;
    private FloatingActionButton addEvent;
    private String[] name = {"Gengs Gelut", "Gengs Wacana", "Gengs Gelut", "Gengs Gelut", "Gengs Wacana"};
    private String[] deadline = {"Gengs Gelut", "Gengs Wacana", "Gengs Gelut", "Gengs Gelut", "Gengs Wacana"};
    private String[] currentBalance = {"Gengs Gelut", "Gengs Wacana", "Gengs Gelut", "Gengs Gelut", "Gengs Wacana"};
    private String[] targetBalance = {"Gengs Gelut", "Gengs Wacana", "Gengs Gelut", "Gengs Gelut", "Gengs Wacana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        //Toolbar Section
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //List View Section
        listView = (ListView) findViewById(R.id.event_list);
        adapter = new EventListAdapter(this, getEventList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Dialog
                final Dialog dialog = new Dialog(EventActivity.this);
                dialog.setContentView(R.layout.activity_pay_event);
                TextView targetBalance = (TextView) dialog.findViewById(R.id.target_balance);
                targetBalance.setText("Rp 100.000.000");
                TextView eventName = (TextView) dialog.findViewById(R.id.event_name);
                eventName.setText("America Tour");
                TextView eventDescription = (TextView) dialog.findViewById(R.id.event_description);
                eventDescription.setText("Traveling to America from 17 until 28 August 2017");
                Button dialogButton = (Button) dialog.findViewById(R.id.pay_btn);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        //addGroupButton
        addEvent = (FloatingActionButton) findViewById(R.id.add_event);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddGroup = new Intent(EventActivity.this, CreateEventActivity.class);
                startActivity(intentAddGroup);
            }
        });
    }

    private List<Event> getEventList() {
        for (int i = 0; i < name.length; i++) {
            Event event = new Event();
            event.setName(name[i]);
            event.setDeadline(deadline[i]);
            event.setCurrentBalance(currentBalance[i]);
            event.setTargetBalance(targetBalance[i]);
            eventList.add(event);
        }
        return eventList;
    }
}
