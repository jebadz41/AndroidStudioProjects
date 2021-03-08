package com.example.challengefragmentwithrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private ArrayList<Car> car;

    ItemClicked activity;

    public interface  ItemClicked
    {
        void  onItemClicked(int index);
    }

    public CarAdapter(Context context,ArrayList<Car> list)
    {
        car = list;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView carLogo;
        TextView tvCarModel, tvOwnerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            carLogo = itemView.findViewById(R.id.carLogo);
            tvCarModel = itemView.findViewById(R.id.tvCarModel);
            tvOwnerName = itemView.findViewById(R.id.tvOwnerName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    activity.onItemClicked(car.indexOf((Car)v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CarAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(car.get(position));

        holder.tvCarModel.setText(car.get(position).getCar_model());
        holder.tvOwnerName.setText(car.get(position).getOwner_name());

        if(car.get(position).getCar_logo().equals("volkswagen"))
            holder.carLogo.setImageResource(R.drawable.volkswagen);
        else if(car.get(position).getCar_logo().equals("nissan"))
            holder.carLogo.setImageResource(R.drawable.nissan);
        else
            holder.carLogo.setImageResource(R.drawable.mercedes);

    }

    @Override
    public int getItemCount() {
        return car.size();
    }
}
