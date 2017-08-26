package com.example.finhacks;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 8/26/2017.
 */
public class AddGroupActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private List<Contact> contactList = new ArrayList<Contact>();
    private ListView listView;
    private AddGroupAdapter adapter;
    private String[] name = {"Gerry Kastogi", "Edwin Wijaya", "Jessica Andjani", "Randi Chilyon", "Elvan Owen"};
    private String[] phoneNumber = {"08123456789", "08123456789", "08123456789", "08123456789", "08123456789"};
    private FloatingActionButton addMember;
    public  ArrayList selectedMemberID = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        //Toolbar Section
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //List View Section
        listView = (ListView) findViewById(R.id.contact_list);
        adapter = new AddGroupAdapter(this, getContactList());
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        //Select Multiple Contact
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode,
                    int position, long id, boolean checked) {
                final int checkedCount = listView.getCheckedItemCount();
                mode.setTitle(checkedCount + " Selected");
                adapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return true;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                adapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
        });
        //Float Action Button
        addMember = (FloatingActionButton) findViewById(R.id.choose_group);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialog
                final Dialog dialog = new Dialog(AddGroupActivity.this);
                dialog.setContentView(R.layout.activity_create_group);

                Button dialogButton = (Button) dialog.findViewById(R.id.create_btn);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
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
