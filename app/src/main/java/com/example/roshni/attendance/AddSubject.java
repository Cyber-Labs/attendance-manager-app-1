package com.example.roshni.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddSubject extends AppCompatActivity
{
    Button b;
    EditText e1,e2;
    DataBaseHelper mmyDb;
    SubjectAdapter subjects;
    ArrayList<subject> names;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subject);
        b=(Button)findViewById(R.id.enter_add_subject);
        e1=(EditText)findViewById(R.id.enter_subject_name);
        e2=(EditText)findViewById(R.id.enter_minimum_percent);
        mmyDb=new DataBaseHelper(this);
        names=new ArrayList<>();
        subjects=new SubjectAdapter(this,names);

    }
    public void ADDSUBJECT(View view)
    {
        String subject_name=e1.getText().toString();

        String subject_minimum=e2.getText().toString();
        long insertdata= mmyDb.insertData(subject_name,subject_minimum);
        if(insertdata==-1)
            Toast.makeText(AddSubject.this,"Error Occured",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(AddSubject.this,"Successful",Toast.LENGTH_SHORT).show();
        e1.setText("");
        e2.setText("");
        subjects.notifyDataSetChanged();
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);

    }
}
