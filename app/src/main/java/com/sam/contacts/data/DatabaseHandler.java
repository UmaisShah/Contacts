package com.sam.contacts.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sam.contacts.R;
import com.sam.contacts.model.Contact;
import com.sam.contacts.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.Database_Name, null, Util.Database_Version);
    }

    //create our table
    @Override
    public void onCreate(SQLiteDatabase db) {
         String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.Table_Name +"("
                + Util.Key_ID +" INTEGER PRIMARY KEY," + Util.Key_Name + " TEXT,"
                + Util.Key_PhoneNumber + " TEXT" + ")";
        Log.d("query", "onCreate: "+CREATE_CONTACT_TABLE);
        db.execSQL(CREATE_CONTACT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE= String.valueOf(R.string.drop_query);
        db.execSQL(DROP_TABLE, new String[]{Util.Database_Name});

        //create table again
        onCreate(db);

    }

    //CRUD Operations

    public void add_Contact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Util.Key_Name,contact.getName());
        values.put(Util.Key_PhoneNumber,contact.getPhone_number());
        db.insert(Util.Table_Name,null,values);

        Log.d("DBHandler", "addContact: " + "item added");
        db.close();
    }

    public Contact getContact(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(Util.Table_Name,new String[]{String.valueOf(Util.Key_ID),Util.Key_Name,Util.Key_PhoneNumber},
                Util.Key_ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        if (cursor!=null)
            //change row
            cursor.moveToFirst();
            Contact contact=new Contact();
            //change column to get data
            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setPhone_number(cursor.getString(2));
            return contact;

    }

    //Get all Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        //Select all contacts
        String selectAll = "SELECT * FROM " + Util.Table_Name;
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact("James", "213986");
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone_number(cursor.getString(2));

                //add contact objects to our list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }

        return contactList;
    }

    //Update contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.Key_Name, contact.getName());
        values.put(Util.Key_PhoneNumber, contact.getPhone_number());

        //update the row
        //update(tablename, values, where id = 43)
        return db.update(Util.Table_Name, values, Util.Key_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }

    //Delete single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.Table_Name, Util.Key_ID + "=?",
                new String[]{String.valueOf(contact.getId())});

        db.close();
    }

    //Get contacts count
    public int getCount() {
        String countQuery = "SELECT * FROM " + Util.Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();

    }
}
