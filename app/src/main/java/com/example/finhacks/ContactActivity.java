package com.example.finhacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
 * Created by asus on 8/27/2017.
 */
public class ContactActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private List<Contact> contactList = new ArrayList<Contact>();
    private ListView listView;
    private ContactListAdapater adapter;
    private String GET_JSON_DATA_HTTP_URL = "http://192.168.43.86:8000/api/bca/getAllUser";
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    private String JSON_CONTACT_NAME = "name";
    private String JSON_PHONE_NUMBER = "phoneNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getContactData();
        //Toolbar Section
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //List View Section
        listView = (ListView) findViewById(R.id.contact_list);
    }

    public void getContactData() {
        contactList.clear();
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
            Contact contact = new Contact();

            JSONObject json = null;
            try{
                json = response.getJSONObject(i);
                contact.setName(json.getString(JSON_CONTACT_NAME));
                contact.setPhoneNumber(json.getString(JSON_PHONE_NUMBER));
            }catch (JSONException e){
                e.printStackTrace();
            }
            contactList.add(contact);
        }
        for (int j=0; j<2; j++){
            Log.d("element", contactList.get(j).getName());
            Log.d("element", contactList.get(j).getPhoneNumber());
        }
        adapter = new ContactListAdapater(this, contactList);
        listView.setAdapter(adapter);
    }
}
