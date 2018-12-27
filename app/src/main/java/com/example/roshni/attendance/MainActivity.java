package com.example.roshni.attendance;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    DataBaseHelper myDb;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    Button b1,b2;
    ListView listView;
    SubjectAdapter subjects;
    ArrayList<subject> names;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        names=new ArrayList<>();
        subjects=new SubjectAdapter(this,names);
        listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(subjects);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Cursor res1=myDb.getAllData();
                for(int z=0;z<=i;z++)
                {
                    res1.moveToNext();
                }
                String mod_id=res1.getString(0);
                String mod_sub_name=res1.getString(1);
                String mod_min_percent=res1.getString(5);
                String mod_present=res1.getString(2);
                String mod_absent=res1.getString(3);
                LayoutDialog layoutDialog = new LayoutDialog(MainActivity.this,mod_id,mod_sub_name,mod_min_percent,mod_present,mod_absent);
                layoutDialog.show(getSupportFragmentManager(),"layout dialog");

            }
        });



        myDb=new DataBaseHelper(this);
        b1=(Button)findViewById(R.id.add);
        b2=(Button)findViewById(R.id.view);


        dl=(DrawerLayout)findViewById(R.id.drawer_layout);
        abdt=new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav=(NavigationView)findViewById(R.id.nav_view);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int id=item.getItemId();
                if(id==R.id.nav_addsubject) {
                    Toast.makeText(MainActivity.this, "Clicked on ADD", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,AddSubject.class);
                    startActivity(i);
                }
                if(id==R.id.nav_removesubject) {
                    Toast.makeText(MainActivity.this, "Clicked on DELETE", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,DeleteSubject.class);
                    startActivity(i);
                }
                if(id==R.id.nav_profile)
                    Toast.makeText(MainActivity.this,"Clicked on PROFILE",Toast.LENGTH_SHORT).show();
                if(id==R.id.nav_loogut)
                    Toast.makeText(MainActivity.this,"Clicked on LOGOUT",Toast.LENGTH_SHORT).show();
                if(id==R.id.nav_help)
                {
                    Intent intent=new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Hey, i've just encountered an error");
                    intent.putExtra(Intent.EXTRA_TEXT,"This bug has been sent to you by ...");
                    intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"rishabh.agarwal997@gmail.com","kritikgarg123@gmail.com"});
                    startActivity(intent);
                }
                if(id==R.id.nav_developers)
                {
                    Intent i=new Intent(MainActivity.this,developers.class);
                    startActivity(i);
                }
                return true;
            }
        });



        Cursor res=myDb.getAllData();
        while (res.moveToNext())
        {
            String s0=res.getString(0);
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
                c=(a*100)/(a+b);
            String s5=String.valueOf(c);
            names.add(new subject(s0,s1,s2,s3,s4,s5));
        }

    }

    public void Refresh(View view)
    {
        Intent i=new Intent(MainActivity.this,MainActivity.class);
        startActivity(i);
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i=new Intent(MainActivity.this,MainActivity.class);
                        startActivity(i);

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("no", null).show();
    }

    public void PADD(View view)
    {
        int position=(Integer)view.getTag();
        String id=names.get(position).getmId();
        //Toast.makeText(MainActivity.this,id,Toast.LENGTH_SHORT).show();
        String present=names.get(position).getmPresent();
        String absent=names.get(position).getmAbsent();
        int p=Integer.parseInt(present);
        int a=Integer.parseInt(absent);
        p++;
        present=""+p;
        int cur;
        if(a+p==0)
            cur=0;
        else
            cur=(p*100)/(a+p);
        String current=""+cur;
        //Toast.makeText(MainActivity.this,present,Toast.LENGTH_SHORT).show();
        myDb.update_presents(id,present,current);
        Intent i=new Intent(MainActivity.this,MainActivity.class);
        startActivity(i);

    }

    public void PSUBTRACT(View view)
    {
        int position=(Integer)view.getTag();
        String id=names.get(position).getmId();
        String present=names.get(position).getmPresent();
        String absent=names.get(position).getmAbsent();
        int p=Integer.parseInt(present);
        int a=Integer.parseInt(absent);
        if(p==0)
        {
            Toast.makeText(MainActivity.this,"Already Zero",Toast.LENGTH_SHORT).show();
        }
        else {
            p--;
            present = String.valueOf(p);
            int cur;
            if (a + p == 0)
                cur = 0;
            else
                cur = (p * 100) / (a + p);
            String current = String.valueOf(cur);
            myDb.update_presents(id, present, current);
            subjects.notifyDataSetChanged();
            Intent i=new Intent(MainActivity.this,MainActivity.class);
            startActivity(i);

        }
    }

    public void AADD(View view)
    {
        int position=(Integer)view.getTag();
        String id=names.get(position).getmId();
        String present=names.get(position).getmPresent();
        String absent=names.get(position).getmAbsent();
        int p=Integer.parseInt(present);
        int a=Integer.parseInt(absent);
        a++;
        absent=String.valueOf(a);
        int cur;
        if(a+p==0)
            cur=0;
        else
            cur=(p*100)/(a+p);
        String current=String.valueOf(cur);
        myDb.update_absents(id,absent,current);
        subjects.notifyDataSetChanged();
        Intent i=new Intent(MainActivity.this,MainActivity.class);
        startActivity(i);

    }

    public void ASUBTRACT(View view)
    {
        int position=(Integer)view.getTag();
        String id=names.get(position).getmId();
        String present=names.get(position).getmPresent();
        String absent=names.get(position).getmAbsent();
        int p=Integer.parseInt(present);
        int a=Integer.parseInt(absent);
        if(a==0)
        {
            Toast.makeText(MainActivity.this,"Already Zero",Toast.LENGTH_SHORT).show();
        }
        else {
            a--;
            absent = String.valueOf(a);
            int cur;
            if (a + p == 0)
                cur = 0;
            else
                cur = (p * 100) / (a + p);
            String current = String.valueOf(cur);
            myDb.update_absents(id, absent, current);
            subjects.notifyDataSetChanged();
            Intent i=new Intent(MainActivity.this,MainActivity.class);
            startActivity(i);



        }
    }


   /* public void openDialog(){
        LayoutDialog layoutDialog = new LayoutDialog();
        layoutDialog.show(getSupportFragmentManager(),"layout dialog");
    }*/
}
