package com.saidur.sqldb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saidur.sqldb.db.MyDbHelper;
import com.saidur.sqldb.ui.AllFarmerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
MyDbHelper myDbHelper;
EditText name,fname,status;
Button insert,showAllBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbHelper=new MyDbHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase=myDbHelper.getWritableDatabase();

    /*    fahimDb=new FahimDb(this);
        SQLiteDatabase mydb=fahimDb.getWritableDatabase();*/

        initView();
    }

    private void initView() {
        name=findViewById(R.id.name);
        fname=findViewById(R.id.fname);
        status=findViewById(R.id.status);
        insert=findViewById(R.id.insertBtn);
        showAllBtn=findViewById(R.id.showAllBtn);
        insert.setOnClickListener(this);
        showAllBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.insertBtn:
               // insertFarmarData();
                String Name=name.getText().toString();
                String Fname=fname.getText().toString();
                String Status=status.getText().toString();

                long rowId=myDbHelper.insertFarmer(Name,Fname,Status);
                if(rowId==-1)
                {
                    Toast.makeText(this, "Not Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Inserted"+rowId, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.showAllBtn:
              //  myDbHelper.show_AllDistrict_Data();
            //    myDbHelper.show_AllFarmer_Data();
                Intent in=new Intent(MainActivity.this, AllFarmerActivity.class);
                startActivity(in);
                break;
        }
    }


/*    private long insertFarmarData() {
        String id=sid.getText().toString();
        String name=sname.getText().toString();
        String dep=sdep.getText().toString();

      long rowId=myDbHelper.insertFarmer(id,name,dep);
      if(rowId==-1)
      {
          Toast.makeText(this, "Not Inserted", Toast.LENGTH_SHORT).show();
      }
      else {
          Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
      }
      return rowId;
    }*/
}