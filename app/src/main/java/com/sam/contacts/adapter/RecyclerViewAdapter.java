package com.sam.contacts.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.contacts.DetailActivity;
import com.sam.contacts.MainActivity;
import com.sam.contacts.R;
import com.sam.contacts.data.DatabaseHandler;
import com.sam.contacts.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_layout_for_recyclerview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact=contactList.get(position);  //each contact object inside of our list

        //setting data
        holder.name.setText(contact.getName());
        holder.number.setText(contact.getPhone_number());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public TextView number;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            itemView.setOnClickListener(this);
            imageView=itemView.findViewById(R.id.imageview);
            imageView.setOnClickListener(this);

            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageview:
                    int position=getAdapterPosition();
                    Contact contact=contactList.get(position);
//            switch (v.getId()){
//                case R.id.imageview:
//                    Log.d("onClick event", "onClick: "+contact.getName());
//                break;
//                    Intent intent=new Intent(context, DetailActivity.class);
//                    intent.putExtra("name",contact.getName());
//                    intent.putExtra("phone",contact.getPhone_number());
//
//                    context.startActivity(intent);

                    DatabaseHandler db = new DatabaseHandler(context);
                    db.deleteContact(contact);

                    //update recycler view for item deletion
                    contactList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, contactList.size());


                    break;
            }

            }

        }
    }

