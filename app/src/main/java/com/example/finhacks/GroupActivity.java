package com.example.finhacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 8/26/2017.
 */
public class GroupActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private List<Group> groupList = new ArrayList<Group>();
    private ListView listView;
    private GroupListAdapter adapter;
    private FloatingActionButton addGroupButton;
    private String[] name = {"Gengs Gelut", "Gengs Wacana", "Gengs Gelut", "Gengs Gelut", "Gengs Wacana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        //Toolbar Section
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //List View Section
        listView = (ListView) findViewById(R.id.group_list);
        adapter = new GroupListAdapter(this, getGroupList());
        listView.setAdapter(adapter);
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

    private List<Group> getGroupList() {
        for (int i = 0; i < name.length; i++) {
            Group group = new Group();
            group.setName(name[i]);
            groupList.add(group);
        }
        return groupList;
    }
}
