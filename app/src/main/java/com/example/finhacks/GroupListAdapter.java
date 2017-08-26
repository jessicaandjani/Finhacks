package com.example.finhacks;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by asus on 8/26/2017.
 */
public class GroupListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Group> groupItems;

    public GroupListAdapter(Activity activity, List<Group> groupItems) {
        this.activity = activity;
        this.groupItems = groupItems;
    }

    @Override
    public int getCount() {
        return groupItems.size();
    }

    @Override
    public Object getItem(int location) {
        return groupItems.get(location);
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
            convertView = inflater.inflate(R.layout.activity_group_list, null);

        TextView groupName = (TextView) convertView.findViewById(R.id.group_name);
        Group group = groupItems.get(position);
        groupName.setText(group.getName());

        return convertView;
    }

}
