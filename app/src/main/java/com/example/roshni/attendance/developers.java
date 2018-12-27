package com.example.roshni.attendance;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class developers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
    }

    public void shridhar(View view) {
        String url="http://github.com/ShridharGoel";
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        Intent choser=Intent.createChooser(i,"Web Browser");
        startActivity(choser);

    }
    public void rishabh(View view) {
        String url="http://github.com/rishabh-997";
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        Intent choser=Intent.createChooser(i,"Web Browser");
        startActivity(choser);

    }
    public void kritik(View view) {
        String url="http://github.com/KritikGarg1";
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        Intent choser=Intent.createChooser(i,"Web Browser");
        startActivity(choser);

    }
    public void aman(View view) {
        String url="http://github.com/Aman563";
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        Intent choser=Intent.createChooser(i,"Web Browser");
        startActivity(choser);

    }
    public void sujay(View view) {
        String url="http://github.com/sujaykaushik008";
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        Intent choser=Intent.createChooser(i,"Web Browser");
        startActivity(choser);

    }
}
