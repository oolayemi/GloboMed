package com.olayrmi.globomed.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.olayrmi.globomed.Model.Student;
import com.olayrmi.globomed.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Student> arrayList;

    public MyAdapter(Context context, ArrayList<Student> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mycustomlistview, null);

            TextView li_id = convertView.findViewById(R.id.id_txt);
            TextView li_name = convertView.findViewById(R.id.name_txt);
            TextView li_surname = convertView.findViewById(R.id.surname_txt);
            TextView li_marks = convertView.findViewById(R.id.marks_txt);

            Student student = arrayList.get(position);

            li_id.setText(student.getId());
            li_name.setText(student.getName());
            li_surname.setText(student.getSurname());
            li_marks.setText(student.getMarks());

        return convertView;
    }
}
