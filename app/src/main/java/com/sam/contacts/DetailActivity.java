package com.sam.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView names,number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        names=findViewById(R.id.textView);
        number=findViewById(R.id.textView2);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            String name=bundle.getString("name");
            String phone=bundle.getString("phone");
            number.setText(phone);
            names.setText(name);
        }
    }
}