package com.example.finhacks;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by asus on 8/26/2017.
 */
public class CategoryAdapter extends ArrayAdapter<Integer> {
    private Activity activity;
    private Integer[] drawablesResource;
    private String[] names;

    public CategoryAdapter(Activity context, int resource, Integer[] objects, String[] names) {
        super(context, resource, objects);
        this.activity = context;
        this.drawablesResource = objects;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_category, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //set drawable to imageview
        holder.categoryImage.setImageResource(getItem(position));
        holder.categoryName.setText(names[position]);

        return convertView;
    }

    private class ViewHolder {

        private ImageView categoryImage;
        private TextView categoryName;

        public ViewHolder(View v) {
            categoryImage = (ImageView) v.findViewById(R.id.category_icon);
            categoryName = (TextView) v.findViewById(R.id.category_name);
        }
    }
}
