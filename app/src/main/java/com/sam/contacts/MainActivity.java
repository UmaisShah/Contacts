package com.sam.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sam.contacts.adapter.RecyclerViewAdapter;
import com.sam.contacts.data.DatabaseHandler;
import com.sam.contacts.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        listView = findViewById(R.id.listview);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);


        contactArrayList = new ArrayList<>();
        List<Contact> contactList = db.getAllContacts();

        //loop through data

        for (Contact contact : contactList) {
//            Log.d("MainActivity", "onCreate: " + contact.getName());
            contactArrayList.add(contact);
        }

        //setup adapter
        recyclerViewAdapter=new RecyclerViewAdapter(this,contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
       // recyclerViewAdapter.notifyDataSetChanged();

//
        Contact aa=new Contact("umais","03129307827");
        db.add_Contact(aa);
        db.add_Contact(aa);
        db.add_Contact(aa);
        db.add_Contact(aa);


        //create array adapter
//        arrayAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                contactArrayList
//        );
//
//        //add to our listview
//        listView.setAdapter(arrayAdapter);
//
//        //Attach eventlistener to listview
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("List", "onItemClick: " + contactArrayList.get(position));
//
//            }
//        });
    }
}