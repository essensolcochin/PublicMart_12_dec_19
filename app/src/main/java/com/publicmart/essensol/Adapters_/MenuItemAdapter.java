package com.publicmart.essensol.Adapters_;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.publicmart.essensol.ModelClasses.ItemModel;
import com.publicmart.essensol.R;

import java.util.ArrayList;




public class MenuItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int LAYOUT_HEADER = 0;
    private static final int LAYOUT_CHILD = 1;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<ItemModel> listItemArrayList;
    private int ImageArray[]={R.drawable.juice,R.drawable.fork,R.drawable.tray};


    public MenuItemAdapter(Context context, ArrayList<ItemModel> listItemArrayList) {

        inflater = LayoutInflater.from(context);
        this.context = context;
        this.listItemArrayList = listItemArrayList;
    }

    @Override
    public int getItemCount() {
        return listItemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (listItemArrayList.get(position).isHeader()) {
            return LAYOUT_HEADER;
        } else
            return LAYOUT_CHILD;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;
        if (viewType == LAYOUT_HEADER) {
            View view = inflater.inflate(R.layout.itemhead, parent, false);
            holder = new MyViewHolderHeader(view);
        } else {
            View view = inflater.inflate(R.layout.itembody, parent, false);
            holder = new MyViewHolderChild(view);
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {



        if (holder.getItemViewType() == LAYOUT_HEADER) {
            MyViewHolderHeader vaultItemHolder = (MyViewHolderHeader) holder;
            vaultItemHolder.tvHeader.setText(listItemArrayList.get(position).getName());

//            for (int i =0;i<3;i++)
//            {
//                vaultItemHolder.icon.setImageDrawable(ImageArray[i]);
//            }

        } else {



            final ItemModel   model = listItemArrayList.get(position);
            final MyViewHolderChild childItemHolder = (MyViewHolderChild) holder;
            childItemHolder.tvchild.setText(model.getName());
//            childItemHolder.price.setText(model.getPrice());

        }

    }


    class MyViewHolderHeader extends RecyclerView.ViewHolder {

        TextView tvHeader;
        ImageView icon;

        private MyViewHolderHeader(View itemView) {
            super(itemView);

            tvHeader = (TextView) itemView.findViewById(R.id.head);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }

    }

    class MyViewHolderChild extends RecyclerView.ViewHolder {

        TextView tvchild,price;
        LinearLayout params;
        CheckBox tick;



        private MyViewHolderChild(View itemView) {
            super(itemView);

            tvchild = (TextView) itemView.findViewById(R.id.name);
//            price = itemView.findViewById(R.id.price);


        }



    }
}
