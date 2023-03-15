package com.example.foodster_app_mobileappdevproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RestaurantOpeningAdapter extends RecyclerView.Adapter {
    String[]names;
    String[]prices;
    String[]amounts;
    ItemClickListener itemClickListener;
    LayoutInflater inflater;

    public RestaurantOpeningAdapter(Context context, String[]name, String[]price, String[]amount){
        this.names = name;
        this.prices = price;
        this.amounts = amount;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.restaurant_list_food_stock,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).txtName.setText(names[position]);
        ((ViewHolder)holder).txtPrice.setText(prices[position]);
        ((ViewHolder)holder).txtName.setText(amounts[position]);

    }

    @Override
    public int getItemCount() {
        return names.length;
    }
    void setClickListener(ItemClickListener nItemClickListener){
        itemClickListener = nItemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtName;
        TextView txtPrice;
        TextView txtAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtFoodName);
            txtPrice=itemView.findViewById(R.id.txtFoodPrice);
            txtAmount=itemView.findViewById(R.id.txtFoodAmount);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(itemClickListener!=null) {
                itemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }
}

