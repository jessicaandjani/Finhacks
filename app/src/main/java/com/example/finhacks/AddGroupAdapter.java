package com.example.finhacks;

import android.app.Activity;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus on 8/26/2017.
 */
public class AddGroupAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private LayoutInflater inflater;
    private List<Contact> contactItems;
    private SparseBooleanArray mSelectedItemsIds;

    public AddGroupAdapter(Context context, List<Contact> contactItems) {
        super(context, R.layout.activity_add_group_list, contactItems);
        mSelectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.contactItems = contactItems;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class ViewHolder {
        TextView name;
        TextView phoneNumber;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_add_group_list, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.phoneNumber = (TextView) convertView.findViewById(R.id.phone_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Contact contact = contactItems.get(position);
        holder.name.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());

        return convertView;
    }

    @Override
    public void remove(Contact object) {
        contactItems.remove(object);
        notifyDataSetChanged();
    }

    public List<Contact> getContactPopulation() {
        return contactItems;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}
