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
 * Created by asus on 8/26/2017.
 */
public class GroupActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ArrayList<Group> groupList = new ArrayList<Group>();
    private ListView listView;
    private GroupListAdapter adapter;
    private String GET_JSON_DATA_HTTP_URL = "http://192.168.43.86:8000/api/bca/getGroupById/1";
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    private String JSON_GROUP_NAME = "name";
    private FloatingActionButton addGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        getGroupData();

        //Toolbar Section
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //List View Section
        listView = (ListView) findViewById(R.id.group_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentEvent = new Intent(GroupActivity.this, EventActivity.class);
                startActivity(intentEvent);
            }
        });
        //addGroupButton
        addGroupButton = (FloatingActionButton) findViewById(R.id.add_group);
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddGroup = new Intent(GroupActivity.this, AddGroupActivity.class);
                startActivity(intentAddGroup);
            }
        });

    }

    public void getGroupData() {
        groupList.clear();
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
            Group group = new Group();

            JSONObject json = null;
            try{
                json = response.getJSONObject(i);
                group.setName(json.getString(JSON_GROUP_NAME));
                Log.d("test", json.getString(JSON_GROUP_NAME));
            }catch (JSONException e){
                e.printStackTrace();
            }
            groupList.add(group);
        }
        for (int j=0; j<2; j++){
            Log.d("element", groupList.get(j).getName());
        }
        adapter = new GroupListAdapter(this, groupList);
        listView.setAdapter(adapter);
    }
}
