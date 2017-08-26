package com.example.finhacks;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus on 8/27/2017.
 */
public class ContactListAdapater extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Contact> contactItems;

    public ContactListAdapater(Activity activity, List<Contact> contactItems) {
        this.activity = activity;
        this.contactItems = contactItems;
    }

    @Override
    public int getCount() {
        return contactItems.size();
    }

    @Override
    public Object getItem(int location) {
        return contactItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_contact_list, null);

        TextView contactName = (TextView) convertView.findViewById(R.id.name);
        TextView phoneNumber = (TextView) convertView.findViewById(R.id.phone_number);
        Contact contact = contactItems.get(position);
        contactName.setText(contact.getName());
        phoneNumber.setText(contact.getPhoneNumber());

        return convertView;
    }
}
