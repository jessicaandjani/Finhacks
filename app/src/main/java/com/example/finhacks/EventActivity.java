package com.example.finhacks;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private String GET_JSON_DATA_HTTP_URL = "http://192.168.43.86:8000/api/bca/event/getEventById/1";
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    private String JSON_EVENT_NAME = "name";
    private String JSON_DESCRIPTION = "description";
    private String JSON_TARGET_BALANCE = "targetBalance";
    private String JSON_CURRENT_BALANCE = "currentBalance";
    private String JSON_DEADLINE = "deadline";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        //Toolbar Section
        getEventList();
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //List View Section
        listView = (ListView) findViewById(R.id.event_list);
//        adapter = new EventListAdapter(this, eventList);
//        listView.setAdapter(adapter);
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
//                TextView eventDescription = (TextView) dialog.findViewById(R.id.event_description);
//                eventDescription.setText("Traveling to America from 17 until 28 August 2017");
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

    public void getEventList() {
        eventList.clear();
        Log.d("masuk getdata", "getConfirmationData: ");
        JSON_DATA_WEB_CALL();
    }

    private void JSON_DATA_WEB_CALL() {
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Test1", "Masuk onResponse confirmation activity");
                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Test2", "Masuk error confirmation activity");
                    }
                });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray response) {
        Log.d("Test3", "Masuk parser confirmation activity");
        for(int i=0; i<response.length(); i++){
            Event event = new Event();

            JSONObject json = null;
            try{
                json = response.getJSONObject(i);
                event.setName(json.getString(JSON_EVENT_NAME));
//                event.setDescription(json.getString(JSON_DESCRIPTION));
                event.setCurrentBalance(json.getString(JSON_CURRENT_BALANCE));
                event.setTargetBalance(json.getString(JSON_TARGET_BALANCE));
                event.setDeadline(json.getString(JSON_DEADLINE));
            }catch (JSONException e){
                e.printStackTrace();
            }
            eventList.add(event);
        }
        for (int j=0; j<2; j++){
            Log.d("element", eventList.get(j).getName());
//            Log.d("element", eventList.get(j).getDescription());
            Log.d("element", eventList.get(j).getCurrentBalance());
            Log.d("element", eventList.get(j).getTargetBalance());
        }
        adapter = new EventListAdapter(this, eventList);
        listView.setAdapter(adapter);
    }
}
