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
 * Created by asus on 8/27/2017.
 */
public class ContactActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private List<Contact> contactList = new ArrayList<Contact>();
    private ListView listView;
    private ContactListAdapater adapter;
    private String[] name = {"Gerry Kastogi", "Edwin Wijaya", "Jessica Andjani", "Randi Chilyon", "Elvan Owen"};
    private String[] phoneNumber = {"08123456789", "08123456789", "08123456789", "08123456789", "08123456789"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        //Toolbar Section
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //List View Section
        listView = (ListView) findViewById(R.id.contact_list);
        adapter = new ContactListAdapater(this, getContactList());
        listView.setAdapter(adapter);
    }

    private List<Contact> getContactList() {
        for (int i = 0; i < name.length; i++) {
            Contact contact = new Contact();
            contact.setName(name[i]);
            contact.setPhoneNumber(phoneNumber[i]);
            contactList.add(contact);
        }
        return contactList;
    }
}
