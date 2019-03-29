package com.example.publicmart;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class _CustomArrayAdapterSearch_ extends BaseAdapter {

    private Context activity;
    private List<StationModel> friendList;
    private List<StationModel> searchList;

    public _CustomArrayAdapterSearch_(Context context, List<StationModel> objects) {

        this.activity = context;
        this.friendList = objects;
        this.searchList = new ArrayList<>();
        this.searchList.addAll(friendList);
    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public Object getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.search_item_listview, parent, false);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(friendList.get(position).getStationName());
        holder.category.setText(friendList.get(position).getShortCode());




        return convertView;
    }

    // Filter method
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        friendList.clear();
        if (charText.length()==0){
            searchList.addAll(friendList);
        }
        else {
            for (StationModel model : searchList){
                if (model.getStationName().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    friendList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }


    private class ViewHolder {

        private TextView name,category;

        public ViewHolder(View v) {

            name = (TextView) v.findViewById(R.id.itemname);
            category = (TextView) v.findViewById(R.id.itemcategory);
        }
    }
}