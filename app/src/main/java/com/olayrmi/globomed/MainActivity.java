package com.olayrmi.globomed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.olayrmi.globomed.Adapter.MyAdapter;
import com.olayrmi.globomed.Model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText name, surname, marks;
    Button btn, loadData;
    ListView li;
    ArrayList<Student> arrayList;
    MyAdapter myAdapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        name = findViewById(R.id.editName);
        surname = findViewById(R.id.editSurname);
        marks = findViewById(R.id.editMarks);
        btn = findViewById(R.id.addData);
        //loadData = findViewById(R.id.loadData);
        li = findViewById(R.id.listView);
        arrayList = new ArrayList<>();

        addData();
        loadDataInListView();
        //viewAll();

        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        li.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = arrayList.get(position);
                String sid = student.getId();
                int result = myDb.deleteStudent(sid);
                if (result > 0){
                    Toast.makeText(MainActivity.this, "Student deleted", Toast.LENGTH_SHORT).show();
                    //loadDataInListView();
                    arrayList.remove(student);
                    myAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

        private void loadDataInListView() {
            arrayList = myDb.getAllData();
            myAdapter = new MyAdapter(this, arrayList);
            li.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
        }

    public void addData(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String usersurname = surname.getText().toString();
                String usermarks = marks.getText().toString();

                boolean isInserted = myDb.insertData(username, usersurname, usermarks);

                if (isInserted = true){
                    Toast.makeText(getApplicationContext(), "Inserted successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), " Not Inserted successfully", Toast.LENGTH_SHORT).show();
                }

                loadDataInListView();

            }
        });
    }

    public void viewAll(){
        loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.loadData();
                if (res.getCount() == 0){
                    Toast.makeText(getApplicationContext(), "No data found in the data base", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("Id: " + res.getString(0) + "\n");
                        buffer.append("Name: " + res.getString(1) + "\n");
                        buffer.append("Surname: " + res.getString(2) + "\n");
                        buffer.append("Marks: " + res.getString(3) + "\n\n");
                    }
                    showMessage("Data", buffer.toString());
                }
            }
        });
    }



    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}
