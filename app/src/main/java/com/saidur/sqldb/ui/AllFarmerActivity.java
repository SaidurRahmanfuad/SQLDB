package com.saidur.sqldb.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.saidur.sqldb.R;
import com.saidur.sqldb.adapters.AllFarmerAdapter;
import com.saidur.sqldb.db.MyDbHelper;
import com.saidur.sqldb.model.Farmer;

import java.util.ArrayList;

public class AllFarmerActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Farmer> farmerList = new ArrayList<>();
    AllFarmerAdapter allFarmerAdapter;
    MyDbHelper myDbHelper;
    Farmer frmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_farmer);
        rv = findViewById(R.id.recAllfarmer);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new MyDbHelper(this).show_AllFarmer_Data();
        // Cursor cv=new MyDbHelper(this).show_AllDistrict_Data();
        while (cursor.moveToNext()) {
            frmr = new Farmer(cursor.getString(0), cursor.getString(1)
                    , cursor.getString(2)
                    , cursor.getString(3));
            farmerList.add(frmr);
        }
        allFarmerAdapter = new AllFarmerAdapter(farmerList, this);
        allFarmerAdapter.notifyDataSetChanged();
        rv.setAdapter(allFarmerAdapter);


        swipeDelete();
    }

    private void swipeDelete() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv);
    }

    //swipe for delete
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();

            String id = farmerList.get(position).getId();
            Log.d("TAG", "onSwiped: "+id);
            switch (direction) {
                case ItemTouchHelper.LEFT:

                    myDbHelper=new MyDbHelper(AllFarmerActivity.this);
                    int dlt = myDbHelper.deleteFarmer(id);
                    if (dlt > 0) {
                        Toast.makeText(AllFarmerActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                        farmerList.remove(position);
                        allFarmerAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(AllFarmerActivity.this, "Not Delete", Toast.LENGTH_SHORT).show();
                    }
            }
        }
    };

}