package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener, AdapterView.OnItemClickListener {


    private EditText itemET;
    private Button btn;
    private ListView itemsList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemET= findViewById(R.id.item_edit_text);
        btn=findViewById(R.id.add_btn);
        itemsList=findViewById(R.id.item_lists);

        items=fileHelper.readData(this);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        itemsList.setAdapter(adapter);

        btn.setOnClickListener(this);
        itemsList.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:
                String itemEntered= itemET.getText().toString();
                adapter.add(itemEntered);
                itemET.setText("");

                fileHelper.writeData(items, this);

                Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
    }
}