package com.example.roshni.attendance;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class SubjectAdapter extends ArrayAdapter<subject>
{
    DataBaseHelper myDb;
    private Context mContext;
    private List<subject> subjectList = new ArrayList<>();

    public SubjectAdapter(Context context, ArrayList<subject> list)
    {
        super(context, 0 , list);
        mContext = context;
        subjectList = list;
    }
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        myDb=new DataBaseHelper(mContext);
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_layout,parent,false);

        subject currentSubject = subjectList.get(position);

        final TextView name = (TextView) listItem.findViewById(R.id.subject_name);
        name.setText(currentSubject.getmName());

        final TextView release = (TextView) listItem.findViewById(R.id.minimum);
        release.setText(currentSubject.getmMinimum());

        final TextView present = (TextView) listItem.findViewById(R.id.presents);
        present.setText("Attended : "+currentSubject.getmPresent());

        final TextView absent = (TextView) listItem.findViewById(R.id.absents);
        absent.setText("Bunked : "+currentSubject.getmAbsent());

        final TextView current = (TextView) listItem.findViewById(R.id.current);
        current.setText(currentSubject.getmcurrent());

        final TextView id=(TextView)listItem.findViewById(R.id.id);
        id.setText(currentSubject.getmId());

        Button b1=(Button)listItem.findViewById(R.id.pAdd);
        Button b2=(Button)listItem.findViewById(R.id.pSubtract);
        Button b3=(Button)listItem.findViewById(R.id.aAdd);
        Button b4=(Button)listItem.findViewById(R.id.aSubtract);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String _id=id.getText().toString();

                String _present=present.getText().toString();
                String _absent=absent.getText().toString();
                _present=_present.substring(11);
                _absent=_absent.substring(9);
                int p=Integer.parseInt(_present);
                int a=Integer.parseInt(_absent);
                p++;
                _present=""+p;
                int cur;
                if(a+p==0)
                    cur=0;
                else
                    cur=(p*100)/(a+p);
                String _current=""+cur;
                myDb.update_presents(_id,_present,_current);
                present.setText("Attended : "+_present);
                current.setText(_current);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String _id=id.getText().toString();

                String _present=present.getText().toString();
                String _absent=absent.getText().toString();
                _present=_present.substring(11);
                _absent=_absent.substring(9);
                int p=Integer.parseInt(_present);
                int a=Integer.parseInt(_absent);
                if(p==0)
                    Toast.makeText(mContext,"Already Zero",Toast.LENGTH_SHORT).show();
                else {
                    p--;
                    _present = "" + p;
                    int cur;
                    if (a + p == 0)
                        cur = 0;
                    else
                        cur = (p * 100) / (a + p);
                    String _current = "" + cur;
                    myDb.update_presents(_id, _present, _current);
                    present.setText("Attended : " + _present);
                    current.setText(_current);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String _id=id.getText().toString();
                String _present=present.getText().toString();
                String _absent=absent.getText().toString();
                _present=_present.substring(11);
                _absent=_absent.substring(9);
                int p=Integer.parseInt(_present);
                int a=Integer.parseInt(_absent);
                a++;
                _absent=""+a;
                int cur;
                if(a+p==0)
                    cur=0;
                else
                    cur=(p*100)/(a+p);
                String _current=""+cur;
                myDb.update_absents(_id,_absent,_current);
                absent.setText("Bunked : "+_absent);
                current.setText(_current);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String _id=id.getText().toString();
                String _present=present.getText().toString();
                String _absent=absent.getText().toString();
                _present=_present.substring(11);
                _absent=_absent.substring(9);
                int p=Integer.parseInt(_present);
                int a=Integer.parseInt(_absent);
                if(a==0)
                    Toast.makeText(mContext,"Already Zero",Toast.LENGTH_SHORT).show();
                else {
                    a--;
                    _absent = "" + a;
                    int cur;
                    if (a + p == 0)
                        cur = 0;
                    else
                        cur = (p * 100) / (a + p);
                    String _current = "" + cur;
                    myDb.update_absents(_id, _absent, _current);
                    absent.setText("Bunked : " + _absent);
                    current.setText(_current);
                }
            }
        });
        return listItem;
    }
}
