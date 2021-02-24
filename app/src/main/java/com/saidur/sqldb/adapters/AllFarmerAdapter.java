package com.saidur.sqldb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saidur.sqldb.R;
import com.saidur.sqldb.model.Farmer;

import java.util.ArrayList;

public class AllFarmerAdapter extends RecyclerView.Adapter<AllFarmerAdapter.MyViewHolder> {
    ArrayList<Farmer> farmerArrayList;
    Context context;

    public AllFarmerAdapter(ArrayList<Farmer> farmerArrayList, Context context) {
        this.farmerArrayList = farmerArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AllFarmerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.rec_model_allfarmer,parent,false);
       //MyViewHolder mvh=new MyViewHolder(v);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllFarmerAdapter.MyViewHolder holder, int position) {
     Farmer farmer=farmerArrayList.get(position);

     holder.frid.setText(farmer.getId());
     holder.frname.setText(farmer.getName());
     holder.frfname.setText(farmer.getFname());
     holder.frstatus.setText(farmer.getStatus());

    }

    @Override
    public int getItemCount() {
        return farmerArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView frid,frname,frfname,frstatus;
        public MyViewHolder(@NonNull View iv) {
            super(iv);
            frid=iv.findViewById(R.id.frid);
            frname=iv.findViewById(R.id.frname);
            frfname=iv.findViewById(R.id.frfname);
            frstatus=iv.findViewById(R.id.frstatus);
        }
    }
}
