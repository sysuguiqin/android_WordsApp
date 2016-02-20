package com.happylearning.user;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OperatePersonDB {

	private PersonDB persondb;
	private SQLiteDatabase db;

	public OperatePersonDB(Context context) {
		persondb = new PersonDB(context);
		db = persondb.getWritableDatabase();
	}

	public void add(PersonRecord contact) {
		ContentValues cv = new ContentValues();
		cv.put("countname", contact.countname);
		cv.put("personname", contact.personname);
		cv.put("email", contact.email);
		cv.put("ability", contact.ability);
		cv.put("password", contact.password);
		db.insert(persondb.TABLE_NAME, null, cv);
	}

	public void deleteContact(String countname) {
		db.delete("person", "countname = ?", new String[] { countname });
	}

	public void updateSomeone(PersonRecord contact) {
		ContentValues cv = new ContentValues();
		cv.put("personname", contact.personname);
		cv.put("email", contact.email);
		cv.put("ability", contact.ability);
		cv.put("password", contact.password);
		db.update("person", cv, "countname = ?",
				new String[] { contact.countname });
	}

	public List<PersonRecord> query() {
		ArrayList<PersonRecord> contacts = new ArrayList<PersonRecord>();
		Cursor c = queryTheCursor();
		while (c.moveToNext()) {
			PersonRecord contact = new PersonRecord();
			contact._id = c.getInt(c.getColumnIndex("_id"));
			contact.countname = c.getString(c.getColumnIndex("countname"));
			contact.personname = c.getString(c.getColumnIndex("personname"));
			contact.email = c.getString(c.getColumnIndex("email"));
			contact.ability = c.getInt(c.getColumnIndex("ability"));
			contact.password = c.getString(c.getColumnIndex("password"));
		}
		c.close();
		return contacts;
	}

	public PersonRecord querySomeone(String countname) {
		Cursor c = queryTheCursor();
		while (c.moveToNext()) {
			PersonRecord contact = new PersonRecord();
			contact._id = c.getInt(c.getColumnIndex("_id"));
			contact.countname = c.getString(c.getColumnIndex("countname"));
			contact.personname = c.getString(c.getColumnIndex("personname"));
			contact.email = c.getString(c.getColumnIndex("email"));
			contact.ability = c.getInt(c.getColumnIndex("ability"));
			contact.password = c.getString(c.getColumnIndex("password"));
			if (contact.countname.equals(countname)) {
				return contact;
			}
		}
		c.close();
		return null;
	}

	private Cursor queryTheCursor() {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery("SELECT * FROM person", null);
		return c;
	}

	public void closeDB() {
		db.close();
	}
}