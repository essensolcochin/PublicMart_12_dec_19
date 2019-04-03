package com.example.publicmart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class _CustomArrayAdapterSearch_ extends BaseAdapter {

    private Context activity;
    private List<SearchFilterModel> friendList;
    private List<SearchFilterModel> searchList;

    public _CustomArrayAdapterSearch_(Context context, List<SearchFilterModel> objects) {

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
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.search_item_listview, parent, false);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(friendList.get(position).getBrandName());
        holder.category.setText(friendList.get(position).getShortDesc());


        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Fashion.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("CategoryKey",friendList.get(position).getCategoryKey());
                activity.getApplicationContext().startActivity(intent);
            }
        });

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
            for (SearchFilterModel model : searchList){
                if (model.getBrandName().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    friendList.add(model);
                }
                else  if (model.getShortDesc().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    friendList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }


    private class ViewHolder {

        private TextView name,category;
        private LinearLayout click;
        public ViewHolder(View v) {

            name = (TextView) v.findViewById(R.id.itemname);
            category = (TextView) v.findViewById(R.id.itemcategory);
            click = (LinearLayout) v.findViewById(R.id.click);
        }
    }
}