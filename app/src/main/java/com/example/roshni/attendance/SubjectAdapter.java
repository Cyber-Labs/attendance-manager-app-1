package com.example.roshni.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class SubjectAdapter extends ArrayAdapter<subject>
{
    private Context mContext;
    private List<subject> subjectList = new ArrayList<>();

    public SubjectAdapter(Context context, ArrayList<subject> list)
    {
        super(context, 0 , list);
        mContext = context;
        subjectList = list;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_layout,parent,false);

        subject currentSubject = subjectList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.subject_name);
        name.setText(currentSubject.getmName());

        TextView release = (TextView) listItem.findViewById(R.id.minimum);
        release.setText(currentSubject.getmMinimum());

        TextView present = (TextView) listItem.findViewById(R.id.presents);
        present.setText("Attended : "+currentSubject.getmPresent());

        TextView absent = (TextView) listItem.findViewById(R.id.absents);
        absent.setText("Bunked : "+currentSubject.getmAbsent());

        TextView current = (TextView) listItem.findViewById(R.id.current);
        current.setText(currentSubject.getmcurrent());

        return listItem;
    }
}
