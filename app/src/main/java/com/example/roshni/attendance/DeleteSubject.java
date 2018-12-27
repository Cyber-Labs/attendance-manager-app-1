package com.example.roshni.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteSubject extends AppCompatActivity
{
    EditText e1;
    DataBaseHelper myDb;
    SubjectAdapter subjectAdapter;
    ArrayList<subject> names;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_subject);
        e1=(EditText)findViewById(R.id.enter_id);
        myDb=new DataBaseHelper(this);
    }
    public void DELETESUBJECT(View view)
    {
        String s=e1.getText().toString();
        if(s.length()==0)
        {
            Toast.makeText(DeleteSubject.this, "Empty Field Encountered", Toast.LENGTH_SHORT).show();
        }
        else
            {
            int x = myDb.deleteData(s);
            if (x == -1)
                Toast.makeText(DeleteSubject.this, "Error Occured", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(DeleteSubject.this, "Successful", Toast.LENGTH_SHORT).show();
            e1.setText("");
        }
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(DeleteSubject.this,MainActivity.class);
        startActivity(i);

    }
}