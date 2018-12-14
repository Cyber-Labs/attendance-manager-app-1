package com.example.roshni.attendance;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    DataBaseHelper myDb;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DataBaseHelper(this);
        b1=(Button)findViewById(R.id.add);
        b2=(Button)findViewById(R.id.view);
        ArrayList<subject> names=new ArrayList<>();
        Cursor res=myDb.getAllData();
        while (res.moveToNext())
        {
            String s1=res.getString(1);
            String s2=res.getString(5);
            String s3=res.getString(2);
            String s4=res.getString(3);
            int a=Integer.parseInt(s3);
            int b=Integer.parseInt(s4);
            int c;
            if(a+b==0)
                c=0;
            else
                c=(a/(a+b))*100;
            String s5=String.valueOf(c);
            names.add(new subject(s1,s2,s3,s4,s5));
        }
        SubjectAdapter subjects=new SubjectAdapter(this,names);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(subjects);
    }

    public void AddData(View view)
    {
        long isinserted=myDb.insertData("Physics","75");
        if(isinserted==-1)
            Toast.makeText(MainActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_SHORT).show();
    }

    public void ViewData(View view)
    {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0)
        {
            //show error message;
            showmessgae("ERROR","Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("ID : "+res.getString(0)+"\n");
            buffer.append("Name : "+res.getString(1)+"\n");
            buffer.append("Minimum : "+res.getString(5)+"\n\n");
            //buffer.append("Marks : "+res.getString(3)+"\n\n");
        }
        //show all data
        showmessgae("DATA",buffer.toString());
    }
    public void showmessgae(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
