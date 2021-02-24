package com.saidur.sqldb.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.saidur.sqldb.R;
import com.saidur.sqldb.adapters.AllFarmerAdapter;
import com.saidur.sqldb.db.MyDbHelper;
import com.saidur.sqldb.model.Farmer;

import java.util.ArrayList;

public class AllFarmerActivity extends AppCompatActivity {
RecyclerView rv;
ArrayList<Farmer> farmerList=new ArrayList<>();
AllFarmerAdapter allFarmerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_farmer);
        rv=findViewById(R.id.recAllfarmer);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor=new MyDbHelper(this).show_AllFarmer_Data();
       // Cursor cv=new MyDbHelper(this).show_AllDistrict_Data();
        while (cursor.moveToNext())
        {
            Farmer frmr=new Farmer(cursor.getString(0),cursor.getString(1)
                    ,cursor.getString(2)
                    ,cursor.getString(3));
            farmerList.add(frmr);
        }
        allFarmerAdapter=new AllFarmerAdapter(farmerList,this);
        rv.setAdapter(allFarmerAdapter);

    }
}