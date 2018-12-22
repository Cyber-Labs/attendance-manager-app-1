package com.example.roshni.attendance;

public class subject
{
    private String mName,mId;
    private String mMinimum,mPresent,mAbsent,mcurrent;

    public subject(String id,String name,String minimum,String present,String absent,String current)
    {
        mId=id;
        mName=name;
        mMinimum=minimum;
        mPresent=present;
        mAbsent=absent;
        mcurrent=current;
    }

    public String getmId()
    {
        return mId;
    }

    public String getmMinimum()
    {
        return mMinimum;
    }

    public String getmName()
    {
        return mName;
    }

    public String getmPresent() { return mPresent; }

    public String getmAbsent() { return mAbsent; }

    public String getmcurrent() {  return mcurrent;  }
}
