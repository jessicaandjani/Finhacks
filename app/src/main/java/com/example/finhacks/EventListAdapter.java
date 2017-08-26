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
 * Created by asus on 8/26/2017.
 */
public class EventListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Event> eventItems;

    public EventListAdapter(Activity activity, List<Event> eventItems) {
        this.activity = activity;
        this.eventItems = eventItems;
    }

    @Override
    public int getCount() {
        return eventItems.size();
    }

    @Override
    public Object getItem(int location) {
        return eventItems.get(location);
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
            convertView = inflater.inflate(R.layout.activity_event_list, null);

        TextView eventName = (TextView) convertView.findViewById(R.id.name);
        TextView deadline = (TextView) convertView.findViewById(R.id.deadline);
        TextView eventStatus = (TextView) convertView.findViewById(R.id.event_status);
        Event event = eventItems.get(position);
        eventName.setText(event.getName());
        deadline.setText(event.getDeadline());
        String status = event.getCurrentBalance() + "/" + event.getTargetBalance();
        eventStatus.setText(status);

        return convertView;
    }
}
