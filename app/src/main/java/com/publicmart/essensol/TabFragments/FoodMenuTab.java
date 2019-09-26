package com.publicmart.essensol.TabFragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.publicmart.essensol.Adapters_.MenuItemAdapter;
import com.publicmart.essensol.ModelClasses.ItemModel;
import com.publicmart.essensol.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodMenuTab extends Fragment {

    RecyclerView foodlist;
    MenuItemAdapter adapter;
    ArrayList<ItemModel>menuItems=new ArrayList<>();
    CardView booknow;

    public FoodMenuTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.food_menu_tab, container, false);

        foodlist=RootView.findViewById(R.id.foodlist);
        booknow=RootView.findViewById(R.id.booknow);
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+918078082721"));
                startActivity(intent);
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        foodlist.setLayoutManager(linearLayoutManager);
//        foodlist.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        menuItems.add(new ItemModel("Milk Shake","00",true));
        menuItems.add(new ItemModel("Sharjha Shake","50 Rs",false));
        menuItems.add(new ItemModel("Banana Shake","50 Rs",false));
        menuItems.add(new ItemModel("Sharjha Shake","50 Rs",false));
        menuItems.add(new ItemModel("Apple Shake","50 Rs",false));
        menuItems.add(new ItemModel("Musk Melon Shake","50 Rs",false));
        menuItems.add(new ItemModel("Mango Shake","50 Rs",false));
        menuItems.add(new ItemModel("Pista Shake","50 Rs",false));
        menuItems.add(new ItemModel("Strawberry Shake","50 Rs",false));
        menuItems.add(new ItemModel("Chocolate Shake","50 Rs",false));
        menuItems.add(new ItemModel("Vanila Shake","50 Rs",false));
        menuItems.add(new ItemModel("ButterScotch Shake","50 Rs",false));
        menuItems.add(new ItemModel("Lichi Shake","50 Rs",false));
        menuItems.add(new ItemModel("Chocolate Bhurma Shake","50 Rs",false));
        menuItems.add(new ItemModel("Chocos Shake","50 Rs",false));
        menuItems.add(new ItemModel("Date Shake","60 Rs",false));
        menuItems.add(new ItemModel("Tuty fruity Shake","60 Rs",false));
        menuItems.add(new ItemModel("Fruit Salad with Ice Cream","70 Rs",false));
        menuItems.add(new ItemModel("Fruit Salad Shake","70 Rs",false));
        menuItems.add(new ItemModel("Chocolate Sharjha Shake","50 Rs",false));
        menuItems.add(new ItemModel("Publicmart Special Dry Fruit Shake","50 Rs",false));
        //
        menuItems.add(new ItemModel("Chinese Food","00",true));
        menuItems.add(new ItemModel("Veg Noodles","100 Rs",false));
        menuItems.add(new ItemModel("Veg Kothey","120 Rs",false));
        menuItems.add(new ItemModel("Chilly Paneer","120 Rs",false));
        menuItems.add(new ItemModel("Honey Chilly Potato","100 Rs",false));
        menuItems.add(new ItemModel("Fried Rice","70 Rs",false));
        menuItems.add(new ItemModel("Hakka Noodles","70 Rs",false));
        menuItems.add(new ItemModel("Singapori Noodles","70 Rs",false));
        menuItems.add(new ItemModel("Veg Chopsy","70 Rs",false));
        //
        menuItems.add(new ItemModel("Special Main Course","00",true));
        menuItems.add(new ItemModel("Chicken Biryani","160 Rs",false));
        menuItems.add(new ItemModel("Hydrabadi Chicken Birynani","180 Rs",false));
        menuItems.add(new ItemModel("Chicken Curry with Jeera Rice","180 Rs",false));
        menuItems.add(new ItemModel("Chicken Kadai with Jeera Rice","180 Rs",false));
        menuItems.add(new ItemModel("Chicken Do Pyaza with Jeera Rice","180 Rs",false));
        menuItems.add(new ItemModel("Egg Biriyani","150 Rs",false));
        menuItems.add(new ItemModel("Chicken Fried Rice","150 Rs",false));
        menuItems.add(new ItemModel("Egg Fried Rice","150 Rs",false));
        //
        menuItems.add(new ItemModel("Falooda","00",true));
        menuItems.add(new ItemModel("Arabian Falooda","120 Rs",false));
        menuItems.add(new ItemModel("Royal","100 Rs",false));
        menuItems.add(new ItemModel("Falooda","100 Rs",false));
        //
        menuItems.add(new ItemModel("Kerala Food","00",true));
        menuItems.add(new ItemModel("Plain Dosa","60 Rs",false));
        menuItems.add(new ItemModel("Masala Dosa","70 Rs",false));
        menuItems.add(new ItemModel("Mysore Masala Dosa","60 Rs",false));
        menuItems.add(new ItemModel("Paneer Dosa","110 Rs",false));
        menuItems.add(new ItemModel("Shahi Dosa","130 Rs",false));
        menuItems.add(new ItemModel("Paper Plain Dosa","80 Rs",false));
        menuItems.add(new ItemModel("Noodles Dosa","120 Rs",false));
        menuItems.add(new ItemModel("Rawa Plain Dosa","100 Rs",false));
        menuItems.add(new ItemModel("Ghee Roast","120 Rs",false));
        menuItems.add(new ItemModel("Ghee Roast Masala Dosa","140 Rs",false));
        //
        menuItems.add(new ItemModel("Non-Veg Snacks","00",true));
        menuItems.add(new ItemModel("Chicken 65","250 Rs",false));
        menuItems.add(new ItemModel("Chilly Chicken","200 Rs",false));
        menuItems.add(new ItemModel("Chicken Crispy Corn","240 Rs",false));
        menuItems.add(new ItemModel("Chicken Lollipop","210 Rs",false));
//        menuItems.add(new ItemModel("Sharjha Shake","50 Rs",false));
//        menuItems.add(new ItemModel("Sharjha Shake","50 Rs",false));
//        menuItems.add(new ItemModel("Sharjha Shake","50 Rs",false));
//        menuItems.add(new ItemModel("Sharjha Shake","50 Rs",false));
//        menuItems.add(new ItemModel("Sharjha Shake","50 Rs",false));


        adapter = new MenuItemAdapter(getContext(), menuItems);
        foodlist.setAdapter(adapter);












        return RootView;
    }

}
