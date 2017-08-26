package com.example.finhacks;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
