package com.example.foodster_app_mobileappdevproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RestaurantOpeningAdapter extends RecyclerView.Adapter {
    String[]orderNums;
    String[]names;
    String[]amounts;
    int[]reminder;
    ItemClickListener itemClickListener;
    LayoutInflater inflater;

    public RestaurantOpeningAdapter(Context context, String[]orderNums, String[]names, String[]amounts, int[]reminder){
        this.orderNums = orderNums;
        this.names = names;
        this.amounts = amounts;
        this.reminder = reminder;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.restaurant_reminder,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).txtOrderNum.setText("Order #"+orderNums[position]);
        String displayNamesFood ="";
        if(names[position].contains("|")){
            String[] namesSepar = names[position].split("\\|");
            String[] amountSepar = amounts[position].split("\\|");
            for(int i=0; i<namesSepar.length; i++){
                displayNamesFood += namesSepar[i]+" ("+amountSepar[i]+")\n ";
            }
        }else{
            displayNamesFood = names[position]+" ("+amounts[position]+")";
        }
        ((ViewHolder)holder).txtNameFood.setText(displayNamesFood);
        if(reminder[position]==1) {
            ((ViewHolder) holder).chboxReminder.setChecked(true);
        }else{
            ((ViewHolder) holder).chboxReminder.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    void setOnClickListener(ItemClickListener nItemClickListener){
        itemClickListener = nItemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtOrderNum;
        TextView txtNameFood;
        CheckBox chboxReminder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderNum=itemView.findViewById(R.id.txtOrderNum);
            txtNameFood=itemView.findViewById(R.id.txtNameFood);
            chboxReminder=itemView.findViewById(R.id.chboxReminder);
            chboxReminder.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(itemClickListener!=null) {
                itemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }
}

