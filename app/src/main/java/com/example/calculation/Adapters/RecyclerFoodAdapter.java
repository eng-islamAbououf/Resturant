package com.example.calculation.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculation.Model.FoodModel;
import com.example.calculation.R;

import java.util.List;

public class RecyclerFoodAdapter extends RecyclerView.Adapter<RecyclerFoodAdapter.MyHolderView>{


    List<FoodModel> foodModelList ;


    @NonNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolderView(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderView holder, int position) {
        FoodModel foodModel = foodModelList.get(position);



    }

    @Override
    public int getItemCount() {
        return foodModelList.size();
    }

    public static class MyHolderView extends RecyclerView.ViewHolder{
        TextView name , price ;
        ImageButton add_btn ;
        ImageView pic ;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.food_name);
            price = itemView.findViewById(R.id.food_price);
            add_btn = itemView.findViewById(R.id.add_btn);
            pic = itemView.findViewById(R.id.food_pic);
        }
    }
}
